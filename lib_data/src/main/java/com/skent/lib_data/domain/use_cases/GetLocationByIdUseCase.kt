package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.models.location.LocationEntity
import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetLocationByIdUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) {
    suspend operator fun invoke(id: Int) : LocationEntity? {
        return repository.getLocationById(id)
    }
}