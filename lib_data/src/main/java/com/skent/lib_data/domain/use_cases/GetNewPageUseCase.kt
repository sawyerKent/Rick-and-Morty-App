package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetNewPageUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
) {
    suspend operator fun invoke(currentPage: Int, newPage: String) : Pair<List<Result>, Int>{
        return repository.getNewPage(currentPage, newPage)
    }
}