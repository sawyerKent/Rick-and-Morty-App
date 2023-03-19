package com.skent.lib_data.domain.repository

import com.skent.lib_data.domain.models.character.Data
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.models.episode.Episode
import com.skent.lib_data.domain.models.episode.EpisodeEntity
import com.skent.lib_data.domain.models.location.Location
import com.skent.lib_data.domain.models.location.LocationEntity
import retrofit2.Response

interface RickAndMortyRepository {
    suspend fun getCharacters()
    suspend fun getCharactersInRange(lower: Int, upper:Int): List<Result>
    suspend fun getCharactersByPage(page: Int): Response<Data>
    suspend fun getNewPage(currentPage: Int, newPage: String): Pair<List<Result>, Int>
    suspend fun numberOfPages() :Int
    suspend fun getCharacterById(id: Int): Result?
    suspend fun getLocations()
    suspend fun getLocationById(id: Int): LocationEntity?
    suspend fun getLocationByName(name: String): Int?
    suspend fun getLocationsByPage(page: Int): Response<Location>
    suspend fun getEpisodes()
    suspend fun getEpisodeById(id: Int): EpisodeEntity?
    suspend fun getEpisodesByPage(page: Int): Response<Episode>
}
