package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) {
    suspend operator fun invoke(id: Int) : Result? {
        return repository.getCharacterById(id)
    }
}