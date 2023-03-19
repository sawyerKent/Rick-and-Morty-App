package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetLocationByNameUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) {
    suspend operator fun invoke(name: String) : Int? {
        return repository.getLocationByName(name)
    }
}