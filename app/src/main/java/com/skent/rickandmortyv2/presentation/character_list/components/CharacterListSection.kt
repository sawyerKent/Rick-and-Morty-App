package com.skent.rickandmortyv2.presentation.character_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skent.rickandmortyv2.presentation.character_list.CharacterListState
import com.skent.rickandmortyv2.presentation.destinations.CharacterDetailsScreenDestination

@Composable
fun CharacterListSection(
    navigator: DestinationsNavigator,
    state: CharacterListState
) {
    state.characters?.let {
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            items(state.characters!!) { character ->
                CharacterItem(
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .height(150.dp)
                        .clickable {
                            navigator
                                .navigate(
                                    CharacterDetailsScreenDestination(
                                        characterId = character.id!!
                                    )
                                )
                        },
                    character = character
                )
            }
        }
    }
}
