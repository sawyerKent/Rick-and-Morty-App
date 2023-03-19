package com.skent.rickandmortyv2.presentation.location_details

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.domain.models.location.EmptyLocationEntity
import com.skent.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.skent.lib_data.domain.use_cases.GetLocationByIdUseCase
import com.skent.lib_data.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the LocationDetailsScreen
 *
 * @param getLocationByIdUseCase GetLocationByIdUseCase
 * @param getCharacterByIdUseCase GetCharacterByIdUseCase
 *
 * @author Sawyer Kent
 */
@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class LocationDetailsViewModel @Inject constructor(
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
): ViewModel(){

    /**
     * Variable to hold a mutable state of LocationEntity
     */
    var location by mutableStateOf(EmptyLocationEntity)
        private set

    /**
     * Variable to hold a mutable state of Result
     */
    var characters: MutableList<Result> by mutableStateOf(mutableListOf())

    /**
     * Gets a location by id
     *
     * @param id Int
     */
    fun getLocationById(id: Int) {
        viewModelScope.launch {
            location = getLocationByIdUseCase(id)!!
        }
    }

    /**
     * Gets a character by id
     *
     * @param id Int
     *
     * @return Result (nullable)
     */
    private suspend fun getCharacterById(id: Int): Result? {
        return getCharacterByIdUseCase(id)
    }

    /**
     * Gets a list of characters
     */
    fun getCharacterList(){
        viewModelScope.launch{
            if(characters.isEmpty())
                for (url in location.residents!!){
                    val charId = url.removePrefix(Constants.CHARACTER_URL_PREFIX).toInt()
                    val char = getCharacterById(charId)!!
                    characters.add(char)
                }
        }
    }
}
