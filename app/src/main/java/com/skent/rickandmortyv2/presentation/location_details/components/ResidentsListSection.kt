package com.skent.rickandmortyv2.presentation.location_details.components

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
import com.skent.rickandmortyv2.presentation.destinations.CharacterDetailsScreenDestination
import com.skent.rickandmortyv2.presentation.location_details.LocationDetailsViewModel

@Composable
fun ResidentsListSection(
    navigator: DestinationsNavigator,
    viewModel: LocationDetailsViewModel
) {
    viewModel.location.residents?.let {
        viewModel.getCharacterList()
        if (viewModel.characters.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(viewModel.characters) { character ->
                    ResidentItem(
                        modifier = Modifier
                            .fillMaxWidth(0.90f)
                            .height(110.dp)
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
}