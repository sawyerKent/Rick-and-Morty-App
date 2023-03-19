package com.skent.rickandmortyv2.presentation.episode_list

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skent.lib_data.domain.models.character.EmptyResult
import com.skent.lib_data.domain.models.episode.EpisodeEntity
import com.skent.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.skent.lib_data.domain.use_cases.GetEpisodeByIdUseCase
import com.skent.lib_data.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the EpisodeListScreen
 *
 * @param getCharacterByIdUseCase GetCharacterByIdUseCase
 * @param getEpisodeByIdUseCase GetEpisodeByIdUseCase
 *
 * @author Sawyer Kent
 */
@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase
) : ViewModel() {

    /**
     * Variable to hold a mutable state of Result
     */
    var character by mutableStateOf(EmptyResult)
        private set

    /**
     * Variable to hold a mutable state of EpisodeEntity
     */
    var episodes: MutableList<EpisodeEntity> by mutableStateOf(mutableListOf())

    /**
     * Gets a character by id
     *
     * @param id Int
     */
    fun getCharacterById(id: Int){
        viewModelScope.launch {
            character = getCharacterByIdUseCase(id)!!
        }
    }

    /**
     * Gets a list of episodes
     */
    fun getEpisodeList(){
        viewModelScope.launch {
            if (episodes.isEmpty())
                for (url in character.episode!!){
                    val episodeId = url!!.removePrefix(Constants.EPISODE_URL_PREFIX).toInt()
                    val episode = getEpisodeByIdUseCase(episodeId)!!
                    episodes.add(episode)
                }
        }
    }
}