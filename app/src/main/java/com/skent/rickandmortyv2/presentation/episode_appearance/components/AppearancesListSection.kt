package com.skent.rickandmortyv2.presentation.episode_appearance.components

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
import com.skent.rickandmortyv2.presentation.episode_appearance.EpisodeAppearanceViewModel
import com.skent.rickandmortyv2.presentation.destinations.CharacterDetailsScreenDestination

@Composable
fun AppearancesListSection(
    navigator: DestinationsNavigator,
    viewModel: EpisodeAppearanceViewModel
) {
    viewModel.episode.name?.let {
        viewModel.getCharacterList()
        if (viewModel.characters.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(viewModel.characters) { character ->
                    AppearanceItem(
                        modifier = Modifier
                            .fillMaxWidth(0.90f)
                            .height(110.dp)
                            .clickable {
                                navigator
                                    .navigate(
                                        CharacterDetailsScreenDestination(
                                            character.id!!
                                        )
                                    )
                            },
                        character = character
                    )
                }
            }
        }
    }
}