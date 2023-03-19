package com.skent.rickandmortyv2.presentation.episode_list.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skent.lib_data.domain.models.episode.EpisodeEntity
import com.skent.rickandmortyv2.presentation.common_components.CustomCanvas
import com.skent.rickandmortyv2.ui.*


@Composable
fun EpisodeItem(
    modifier: Modifier = Modifier,
    episode: EpisodeEntity
) {
    Box(
        modifier = modifier
    ) {
        CustomCanvas(modifier = modifier.matchParentSize())
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            episode.name?.let { episode ->
                Text(
                    text = episode,
                    fontSize = 18.sp,
                    color = White,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (episode.airDate != null) {
                    Text(
                        text = "Air Date: ${episode.airDate}",
                        fontSize = 16.sp,
                        color = White
                    )
                } else {
                    Text(
                        text = "Air Date: unknown",
                        fontSize = 16.sp,
                        color = White
                    )
                }
                if (episode.episode != null) {
                    Text(
                        text = "${episode.episode}",
                        fontSize = 16.sp,
                        color = White,
                        //modifier = Modifier.weight(1f),
                    )
                } else {
                    Text(
                        text = "unknown",
                        fontSize = 16.sp,
                        color = White
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}