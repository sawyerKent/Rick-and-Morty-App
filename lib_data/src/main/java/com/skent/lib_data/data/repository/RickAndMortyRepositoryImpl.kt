package com.skent.lib_data.data.repository

import com.skent.lib_data.data.local.RickAndMortyDatabase
import com.skent.lib_data.data.remote.RickAndMortyApiService
import com.skent.lib_data.domain.models.character.Data
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.models.episode.Episode
import com.skent.lib_data.domain.models.episode.EpisodeEntity
import com.skent.lib_data.domain.models.location.Location
import com.skent.lib_data.domain.models.location.LocationEntity
import com.skent.lib_data.domain.repository.RemoteDataSource
import com.skent.lib_data.domain.repository.RickAndMortyRepository
import com.skent.lib_data.utils.Constants
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.ceil

/**
 * Repository implementation for the RickAndMortyRepository
 *
 * **Parameters injected through Dagger Hilt**
 *
 * @param api RickAndMortyApiService
 * @param db RickAndMortyDatabase
 * @param remoteDataSource RemoteDataSource
 *
 * @author Sawyer Kent
 */
class RickAndMortyRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApiService,
    private val db: RickAndMortyDatabase,
    private val remoteDataSource: RemoteDataSource,
) : RickAndMortyRepository {

    private val characterDao = db.getCharacterDao()
    private val locationDao = db.getLocationDao()
    private val episodeDao = db.getEpisodeDao()

    /**
     * Populates the database with all characters
     */
    override suspend fun getCharacters() {
        val sizeOfCharData = characterDao.getCharDataSize()

        if (sizeOfCharData == 0) {
            var page = 1
            while (true) {
                val chars = getCharactersByPage(page)
                if (chars.body()?.results != null) {
                    characterDao.addCharacterData(chars.body()!!.results!!)
                }
                if (chars.body()?.info?.next == null)
                    break
                page += 1
            }
        }
    }

    /**
     * Populates the database with all Locations
     */
    override suspend fun getLocations() {

        val sizeOfLocationData = locationDao.getNumberOfLocations()

        if (sizeOfLocationData == 0) {
            var page = 1
            while (true) {
                val locations = getLocationsByPage(page)
                if (locations.body()?.locationResults != null) {
                    locationDao.addLocationData(locations.body()!!.locationResults!!)
                }
                if (locations.body()?.info?.next == null)
                    break
                page += 1
            }
        }
    }

    /**
     * Populates the database with all Episodes
     */
    override suspend fun getEpisodes() {

        val sizeOfEpisodeData = episodeDao.getNumberOfEpisodes()

        if (sizeOfEpisodeData == 0) {
            var page = 1
            while (true) {
                val episode = getEpisodesByPage(page)
                if (episode.body()?.episodeResults != null) {
                    episodeDao.addEpisodeData(episode.body()!!.episodeResults)
                }
                if (episode.body()?.info?.next == null)
                    break
                page += 1
            }
        }
    }

    /**
     * Gets a list of characters by page from the Api
     *
     * @param page Int
     *
     * @return Response<Data>
     */
    override suspend fun getCharactersByPage(page: Int): Response<Data> {
        return api.getCharacters(page)
    }

    /**
     * Gets a list of locations by page from the Api
     *
     * @param page Int
     *
     * @return Response<Location>
     */
    override suspend fun getLocationsByPage(page: Int): Response<Location> {
        return api.getLocations(page)
    }

    /**
     * Gets a list of episodes by page from the Api
     *
     * @param page Int
     *
     * @return Response<Episode>
     */
    override suspend fun getEpisodesByPage(page: Int): Response<Episode> {
        return api.getEpisodes(page)
    }

    /**
     * Gets Specific Characters from range from the Database
     *
     * @param lower Int
     * @param upper Int
     *
     * @return List<Result>
     */
    override suspend fun getCharactersInRange(lower: Int, upper: Int): List<Result> {
        return characterDao.getRangeOfCharacters(lower, upper)
    }

    /**
     * Calculates the max page
     *
     * @return Int
     */
    override suspend fun numberOfPages(): Int {
        val sizeOfDatabase = characterDao.getCharDataSize()
        return ceil(sizeOfDatabase.toDouble() / Constants.PAGE_SIZE.toDouble()).toInt()
    }

    /**
     * Gets a new page of characters
     *
     * @param currentPage Int
     * @param newPage String
     *
     * @return Pair<List<Result>, Int>
     */
    override suspend fun getNewPage(
        currentPage: Int,
        newPage: String
    ): Pair<List<Result>, Int> {
        val desiredPage = when (newPage) {
            "first" -> 1
            "prev" -> {
                if (currentPage == 1) 1
                else currentPage - 1
            }
            "next" -> {
                if (currentPage == numberOfPages())
                    numberOfPages()
                else
                    currentPage + 1
            }
            "last" -> numberOfPages()
            else -> 1
        }

        val lower = ((desiredPage - 1) * Constants.PAGE_SIZE) + 1
        val upper = desiredPage * Constants.PAGE_SIZE

        return Pair(getCharactersInRange(lower, upper), desiredPage)
    }

    /**
     * Get a character by Id
     *
     * @param id Int
     *
     * @return Result (nullable)
     */
    override suspend fun getCharacterById(id: Int): Result? {
        return characterDao.getSelectedCharacter(id)
    }

    /**
     * Get a location by Id
     *
     * @param id Int
     *
     * @return LocationEntity (nullable)
     */
    override suspend fun getLocationById(id: Int): LocationEntity? {
        return locationDao.getSelectedLocation(id)
    }

    /**
     * Get a location by name
     *
     * @param name String
     *
     * @return Int (nullable)
     */
    override suspend fun getLocationByName(name: String): Int? {
        return locationDao.getSelectedLocationByName(name)
    }

    /**
     * Get an episode by Id
     *
     * @param id Int
     *
     * @return EpisodeEnitity (nullable)
     */
    override  suspend fun getEpisodeById(id: Int) : EpisodeEntity? {
        return episodeDao.getSelectedEpisode(id)
    }
}