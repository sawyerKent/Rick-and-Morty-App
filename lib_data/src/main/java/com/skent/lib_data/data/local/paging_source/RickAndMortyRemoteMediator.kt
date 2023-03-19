package com.skent.lib_data.data.local.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.skent.lib_data.data.local.RickAndMortyDatabase
import com.skent.lib_data.data.local.dao.CharacterDao
import com.skent.lib_data.data.local.dao.RemoteKeyDao
import com.skent.lib_data.data.remote.RickAndMortyApiService
import com.skent.lib_data.domain.models.character.RemoteKey
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.utils.Constants
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RickAndMortyRemoteMediator @Inject constructor(
    private val apiService: RickAndMortyApiService,
    private val characterDao: CharacterDao,
    private val remoteKeysDao: RemoteKeyDao,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteMediator<Int, Result>() {

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = rickAndMortyDatabase.getRemoteKeysDao().getRemoteKeys(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Result>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            println("it gets here!!!")
            val response = apiService.getCharacters(page).body()!!
            if (response.results!!.isNotEmpty()) {
                rickAndMortyDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        rickAndMortyDatabase.getCharacterDao().deleteAll()
                        rickAndMortyDatabase.getRemoteKeysDao().deleteAllRemoteKeys()
                    }
                    val prevPage = response.info!!.prev?.removePrefix(Constants.PREFIX)?.toIntOrNull()
                    val nextPage = response.info.next?.removePrefix(Constants.PREFIX)?.toIntOrNull()
                    val keys = response.results.map { char ->
                        RemoteKey(
                            id = char.id!!,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = null
                        )
                    }
                    rickAndMortyDatabase.getRemoteKeysDao().addAllRemoteKeys(remoteKeys = keys)
                    rickAndMortyDatabase.getCharacterDao().addCharacterData(characterResult = response.results)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.info!!.next == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                rickAndMortyDatabase.getRemoteKeysDao().getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Result>
    ): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                rickAndMortyDatabase.getRemoteKeysDao().getRemoteKeys(id = character.id!!)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Result>
    ): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                rickAndMortyDatabase.getRemoteKeysDao().getRemoteKeys(id = character.id!!)
            }
    }
}