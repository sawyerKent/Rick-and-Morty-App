package com.skent.lib_data.domain.use_cases

import com.skent.lib_data.domain.models.episode.EpisodeEntity
import com.skent.lib_data.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: RickAndMortyRepository
){
    suspend operator fun invoke(id: Int) : EpisodeEntity?{
        return repository.getEpisodeById(id)
    }
}
