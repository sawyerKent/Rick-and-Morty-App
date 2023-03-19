package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetNumberOfPagesUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) {
    suspend operator fun invoke(): Int{
        return repository.numberOfPages()
    }
}