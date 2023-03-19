package com.skent.rickandmortyv2.presentation.episode_appearance

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
import com.skent.rickandmortyv2.presentation.common_components.*
import com.skent.rickandmortyv2.presentation.episode_appearance.components.AppearancesListSection
import com.skent.rickandmortyv2.presentation.episode_list.components.EpisodeItem
import com.skent.rickandmortyv2.ui.*


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun EpisodeAppearanceScreen(
    navigator: DestinationsNavigator,
    episodeId: Int,
    viewModel: EpisodeAppearanceViewModel = hiltViewModel()
) {
    viewModel.getEpisodeById(episodeId)

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
            EpisodeItem(
                modifier = Modifier
                    .fillMaxWidth(0.90f)
                    .height(80.dp),
                episode = viewModel.episode
            )
            TitleSection(title = "Characters")
            Spacer(modifier = Modifier.height(10.dp))
            AppearancesListSection(
                navigator = navigator,
                viewModel = viewModel
            )
        }
    }
}