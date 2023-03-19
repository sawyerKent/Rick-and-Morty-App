package com.skent.lib_data.data.remote

import com.skent.lib_data.data.local.RickAndMortyDatabase
import com.skent.lib_data.domain.models.character.Data
import com.skent.lib_data.domain.models.location.Location
import com.skent.lib_data.domain.repository.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: RickAndMortyApiService,
    private val rickAndMortyDatabase: RickAndMortyDatabase
) : RemoteDataSource {

    override suspend fun getCharacters(): Response<Data> {
        return api.getCharacters()
    }

    override suspend fun getLocations(page: Int): Response<Location>{
        return api.getLocations(page)
    }
}