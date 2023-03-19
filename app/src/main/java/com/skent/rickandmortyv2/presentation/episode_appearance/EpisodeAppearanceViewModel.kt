package com.skent.rickandmortyv2.presentation.episode_appearance

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.models.episode.EmptyEpisodeEntity
import com.skent.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.skent.lib_data.domain.use_cases.GetEpisodeByIdUseCase
import com.skent.lib_data.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the EpisodeAppearanceScreen
 *
 * @param getCharacterByIdUseCase GetCharacterByIdUseCase
 * @param getEpisodeByIdUseCase GetEpisodeByIdUseCase
 *
 * @author Sawyer Kent
 */
@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class EpisodeAppearanceViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase
) : ViewModel() {

    /**
     * Variable to hold a mutable state of EpisodeEntity
     */
    var episode by mutableStateOf(EmptyEpisodeEntity)
        private set

    /**
     * Variable to hold a mutable state of Result
     */
    var characters: MutableList<Result> by mutableStateOf(mutableListOf())

    /**
     * Gets an episode by id
     *
     * @param id Int
     */
    fun getEpisodeById(id: Int){
        viewModelScope.launch {
            episode = getEpisodeByIdUseCase(id)!!
        }
    }

    /**
     * Gets a list of characters
     */
    fun getCharacterList(){
        viewModelScope.launch {
            if(characters.isEmpty())
                for(url in episode.characters!!){
                    val charId = url.removePrefix(Constants.CHARACTER_URL_PREFIX).toInt()
                    val char = getCharacterByIdUseCase(charId)!!
                    characters.add(char)
                }
        }
    }
}