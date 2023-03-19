package com.skent.rickandmortyv2.presentation.location_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.skent.lib_data.domain.models.character.Result
import com.skent.rickandmortyv2.R
import com.skent.rickandmortyv2.presentation.common_components.CustomCanvas
import com.skent.rickandmortyv2.ui.*

@Composable
fun ResidentItem(
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
                if (character.image != null) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = "Character Image")
                } else {
                    Image(painter = painterResource(
                        id = R.drawable.ic_launcher_background),
                        contentDescription = "Sample Image",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.width(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column() {
                    character.name?.let { name ->
                        if (character.name != null) {
                            Text(
                                text = name,
                                fontSize = 22.sp,
                                color = White
                            )
                        } else {
                            Text(
                                text = "unknown",
                                fontSize = 22.sp,
                                color = White
                            )
                        }
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
                            if (character.status != null) {
                                Text(
                                    text = status,
                                    fontSize = 14.sp,
                                    color = White
                                )
                            } else {
                                Text(
                                    text = "Status unknown",
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
                            if (character.species != null) {
                                Text(
                                    text = species,
                                    fontSize = 14.sp,
                                    color = White
                                )
                            } else {
                                Text(
                                    text = "Species unknown",
                                    fontSize = 14.sp,
                                    color = White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}