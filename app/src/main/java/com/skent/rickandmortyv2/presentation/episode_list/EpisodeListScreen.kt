package com.skent.rickandmortyv2.presentation.episode_list

import androidx.compose.foundation.background


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skent.rickandmortyv2.presentation.common_components.RickAndMortyAppBar
import com.skent.rickandmortyv2.presentation.episode_list.components.EpisodesListSection
import com.skent.rickandmortyv2.presentation.episode_list.components.EpisodeListHeaderSection
import com.skent.rickandmortyv2.ui.*


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun EpisodeListScreen(
    navigator: DestinationsNavigator,
    characterId: Int,
    viewModel: EpisodeListViewModel = hiltViewModel()
) {
    viewModel.getCharacterById(characterId)

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
            EpisodeListHeaderSection(viewModel.character)
            Spacer(modifier = Modifier.height(16.dp))
            EpisodesListSection(
                navigator = navigator,
                viewModel = viewModel
            )
        }
    }
}