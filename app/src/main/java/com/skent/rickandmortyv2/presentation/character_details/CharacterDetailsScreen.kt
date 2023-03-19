package com.skent.rickandmortyv2.presentation.character_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.hilt.navigation.compose.hiltViewModel
import com.skent.rickandmortyv2.presentation.character_details.components.CharacterDetailsSection
import com.skent.rickandmortyv2.presentation.common_components.RickAndMortyAppBar
import com.skent.rickandmortyv2.presentation.common_components.TitleSection
import com.skent.rickandmortyv2.ui.*

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun CharacterDetailsScreen(
    navigator: DestinationsNavigator,
    characterId: Int,
    viewModel: CharacterDetailsViewModel = hiltViewModel()
) {
    viewModel.getCharacterById(characterId)
    val character = viewModel.character

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { RickAndMortyAppBar(navigator = navigator) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGrey),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            character.name?.let { name ->
                if (character.name == null) {
                    TitleSection(title = "Error")
                } else {
                    TitleSection(title = name)
                }
            }
            CharacterDetailsSection(
                navigator = navigator,
                character = character,
                viewModel = viewModel
            )
        }
    }
}