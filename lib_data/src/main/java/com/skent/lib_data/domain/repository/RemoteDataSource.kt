package com.skent.lib_data.domain.repository

import com.skent.lib_data.domain.models.character.Data
import com.skent.lib_data.domain.models.location.Location
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getCharacters() : Response<Data>
    suspend fun getLocations(page: Int) : Response<Location>
}