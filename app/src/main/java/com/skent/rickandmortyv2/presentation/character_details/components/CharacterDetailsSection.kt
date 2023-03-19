package com.skent.rickandmortyv2.presentation.character_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skent.lib_data.domain.models.character.Result
import com.skent.rickandmortyv2.R
import com.skent.rickandmortyv2.presentation.character_details.CharacterDetailsViewModel
import com.skent.rickandmortyv2.presentation.destinations.EpisodeListScreenDestination
import com.skent.rickandmortyv2.presentation.destinations.LocationDetailsScreenDestination
import com.skent.rickandmortyv2.ui.Green
import com.skent.rickandmortyv2.ui.LightGrey
import com.skent.rickandmortyv2.ui.Red
import com.skent.rickandmortyv2.ui.White
import kotlinx.coroutines.launch

@Composable
fun CharacterDetailsSection(
    navigator: DestinationsNavigator,
    character: Result,
    viewModel: CharacterDetailsViewModel
) {
    val scope = rememberCoroutineScope()
    var color = when (character.status) {
        "Alive" -> Green
        "Dead" -> Red
        else -> LightGrey
    }

    if (character.image != null) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character Image"
        )
    } else {
        Image(painter = painterResource(
            id = R.drawable.ic_launcher_background),
            contentDescription = "Character Image",
            modifier = Modifier.size(250.dp)
        )
    }
    Spacer(modifier = Modifier.height(25.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (character.status != null) {
            Text(
                text = "Status: ${character.status}",
                fontSize = 20.sp,
                color = White
            )
        } else {
            Text(
                text = "Status: unknown",
                fontSize = 20.sp,
                color = White
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = "Circle",
            tint = color,
            modifier = Modifier.size(20.dp)
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Row {
        if (character.species != null) {
            Text(
                text = "Species: ${character.species}",
                fontSize = 20.sp,
                color = White
            )
        } else {
            Text(
                text = "Species: unknown",
                fontSize = 20.sp,
                color = White
            )
        }
        Spacer(modifier = Modifier.width(25.dp))
        if (character.gender != null) {
            Text(
                text = "Gender: ${character.gender}",
                fontSize = 20.sp,
                color = White
            )
        } else {
            Text(
                text = "Gender: unknown",
                fontSize = 20.sp,
                color = White
            )
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
    Row() {
        val origin = character
            .origin?.name.toString()
            .substringAfter("=")
            .substringBefore(",")
        Text(
            text = "Origin:",
            fontSize = 20.sp,
            color = White,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = origin,
            fontSize = 20.sp,
            color = LightGrey,
            modifier = Modifier.clickable {
                scope.launch {
                    val tempId = viewModel.getLocationByName(origin)
                    if (tempId != null) {
                        navigator
                            .navigate(
                                LocationDetailsScreenDestination(
                                    locationID = tempId
                                )
                            )
                    }
                }
            }
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Row() {
        val location = character
            .location?.name.toString()
            .substringAfter("name=")
            .substringBefore(",")
        Text(
            text = "Location:",
            fontSize = 20.sp,
            color = White,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = location,
            fontSize = 20.sp,
            color = LightGrey,
            modifier = Modifier.clickable {
                scope.launch {
                    val tempId = viewModel.getLocationByName(location)
                    if (tempId != null) {
                        navigator
                            .navigate(
                                LocationDetailsScreenDestination(
                                    locationID = tempId
                                )
                            )
                    }
                }
            }
        )
    }
    Spacer(modifier = Modifier.height(30.dp))
    Row() {
        val numEpisodes = character.episode?.size ?: 0
        Text(
            text = "Episodes:",
            fontSize = 20.sp,
            color = White,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = numEpisodes.toString(),
            fontSize = 20.sp,
            color = LightGrey,
            modifier = Modifier.clickable {
                navigator
                    .navigate(
                        EpisodeListScreenDestination(
                            characterId = character.id!!
                        )
                    )
            }
        )
    }
}