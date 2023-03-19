package com.skent.rickandmortyv2.presentation.character_list.components

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.skent.lib_data.domain.models.character.Result
import com.skent.lib_data.utils.Constants
import com.skent.rickandmortyv2.presentation.common_components.CustomCanvas
import com.skent.rickandmortyv2.ui.*

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Result
) {
    val color = when(character.status) {
        "Alive" -> Green
        "Dead" -> Red
        else -> LightGrey
    }
    Box(
        modifier = modifier
    ) {
        CustomCanvas(modifier = modifier.matchParentSize())
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.32f),
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                AsyncImage(
                    model = character.image,
                    contentDescription = "Character Image"
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column() {
                    character.name?.let { name ->
                        Text(
                            text = name,
                            fontSize = 22.sp,
                            color = White
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "Circle",
                            tint = color,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        character.status?.let { status ->
                            if (character.species == null) {
                                Text(
                                    text = "unknown",
                                    fontSize = 14.sp,
                                    color = White
                                )
                            } else {
                                Text(
                                    text = status,
                                    fontSize = 14.sp,
                                    color = White
                                )
                            }
                        }
                        Text(
                            text = " - ",
                            fontSize = 14.sp,
                            color = White
                        )
                        character.species?.let { species ->
                            Text(
                                text = species,
                                fontSize = 14.sp,
                                color = White
                            )
                        }
                    }
                    Text(
                        text = "Last Known Location:",
                        fontSize = 14.sp,
                        color = LightGrey,
                        fontStyle = FontStyle.Italic
                    )
                    character.location?.name?.let { locationName ->
                        if (character.location?.name == null) {
                            Text(
                                text = "unknown location",
                                fontSize = 14.sp,
                                color = White
                            )
                        } else {
                            Text(
                                text = locationName
                                    .substringAfter("=")
                                    .substringBefore(","),
                                fontSize = 14.sp,
                                color = White
                            )
                        }
                    }
                    Text(
                        text = "First Seen In:",
                        fontSize = 14.sp,
                        color = LightGrey,
                        fontStyle = FontStyle.Italic
                    )
                    character.episode?.get(0)?.let { episode ->
                        if (character.episode.isNullOrEmpty()) {
                            Text(
                                text = "unknown episode",
                                fontSize = 14.sp,
                                color = White
                            )
                        }
                        Text(
                            text = "Episode ${episode.removePrefix(Constants.EPISODE_URL_PREFIX)}",
                            fontSize = 14.sp,
                            color = White
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}