package com.skent.rickandmortyv2.presentation.character_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skent.lib_data.domain.models.character.EmptyResult
import com.skent.lib_data.domain.use_cases.GetCharacterByIdUseCase
import com.skent.lib_data.domain.use_cases.GetLocationByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the CharacterDetailsScreen
 *
 * @param getCharacterByIdUseCase GetCharacterByIdUseCase
 * @param getLocationByNameUseCase GetLocationByNameUseCase
 *
 * @author Sawyer Kent
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getLocationByNameUseCase: GetLocationByNameUseCase
) : ViewModel() {

    /**
     * Variable to hold a mutable state of Result
     */
    var character by mutableStateOf(EmptyResult)
        private set

    /**
     * Gets a character by id from the repository
     *
     * @param id Int
     */
    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            character = getCharacterByIdUseCase(id)!!
        }
    }

    /**
     * Gets a location by name from the repository
     *
     * @param name String
     *
     * @return Int (nullable)
     */
    suspend fun getLocationByName(name: String): Int? {
        return getLocationByNameUseCase(name)
    }
}