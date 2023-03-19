package com.skent.rickandmortyv2.presentation.character_list

import com.skent.lib_data.domain.models.character.Result

data class CharacterListState(
    var isLoading: Boolean = false,
    var error: String? = null,
    var characters: List<Result>? = null,
    var pageNumber: Int = 1
)

