package com.skent.rickandmortyv2.presentation.character_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skent.lib_data.domain.use_cases.CacheDatabaseUseCase
import com.skent.lib_data.domain.use_cases.GetNewPageUseCase
import com.skent.lib_data.domain.use_cases.GetNumberOfPagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the CharacterListScreen
 *
 * @param getNewPageUseCase GetNewPageUseCase
 * @param getNumberOfPagesUseCase GetNumberOfPagesUseCase
 * @param cacheDatabaseUseCase CacheDatabaseUseCase
 *
 * @author Sawyer Kent
 */
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getNewPageUseCase: GetNewPageUseCase,
    private val getNumberOfPagesUseCase: GetNumberOfPagesUseCase,
    private val cacheDatabaseUseCase: CacheDatabaseUseCase
) : ViewModel() {

    /**
     * Variable to hold a mutable state of CharacterListState
     */
    var state by mutableStateOf(CharacterListState())
        private set

    /**
     * Variable to hold a mutable state of Int
     */
    var pageCount by mutableStateOf(0)
        private set

    /**
     * Variable to hold a mutable state of Boolean
     */
    var start by mutableStateOf(false)
        private set

    /**
     * Initializes the character list state
     */
    init {
        state = state.copy(
            isLoading = true,
            error = null
        )
        viewModelScope.launch {
            start = cacheDatabaseUseCase()
            getInitPage()
            pageCount()
        }
    }

    /**
     * Gets the first page of characters
     */
    private fun getInitPage(){
        viewModelScope.launch {
            getNewPageUseCase(state.pageNumber,"first").run{
                state = state.copy(
                    isLoading = false,
                    error = null,
                    characters = this.first,
                    pageNumber = this.second
                )
            }
        }
    }

    /**
     * Gets a new page
     *
     * @param page String
     */
    fun getNewPage(page: String){
        state = state.copy(
            isLoading = true,
            error = null,
            characters = null
        )
        viewModelScope.launch {
            getNewPageUseCase(state.pageNumber, page).let{
                state = state.copy(
                    isLoading = false,
                    error = null,
                    characters = it.first,
                    pageNumber = it.second
                )
            }
            pageCount()
        }
    }

    /**
     * Gets the page count
     */
    fun pageCount() {
        viewModelScope.launch {
            pageCount = getNumberOfPagesUseCase()
        }
    }
}
