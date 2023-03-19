package com.skent.lib_data.data.remote

import com.skent.lib_data.domain.models.character.Data
import com.skent.lib_data.domain.models.episode.Episode
import com.skent.lib_data.domain.models.location.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): Response<Data>

    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int = 1
    ) : Response<Location>

    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int = 1
    ) : Response<Episode>
}
