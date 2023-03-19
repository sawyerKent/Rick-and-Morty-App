package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class CacheDatabaseUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) {
    suspend operator fun invoke() : Boolean {
        repository.getCharacters()
        repository.getLocations()
        repository.getEpisodes()
        return true
    }

}