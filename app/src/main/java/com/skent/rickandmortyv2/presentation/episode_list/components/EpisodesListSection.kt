package com.skent.rickandmortyv2.presentation.episode_list.components

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
import com.skent.rickandmortyv2.presentation.destinations.EpisodeAppearanceScreenDestination
import com.skent.rickandmortyv2.presentation.episode_list.EpisodeListViewModel

@Composable
fun EpisodesListSection(
    navigator: DestinationsNavigator,
    viewModel: EpisodeListViewModel
) {
    viewModel.character.episode?.let {
        viewModel.getEpisodeList()
        if (viewModel.episodes.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(viewModel.episodes) { episode ->
                    EpisodeItem(
                        modifier = Modifier
                            .fillMaxWidth(0.90f)
                            .height(100.dp)
                            .clickable {
                                navigator
                                    .navigate(
                                        EpisodeAppearanceScreenDestination(
                                            episode.id!!
                                        )
                                    )
                            },
                        episode = episode
                    )
                }
            }
        }
    }
}