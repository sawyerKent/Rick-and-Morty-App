package com.skent.rickandmortyv2.presentation.common_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skent.rickandmortyv2.presentation.destinations.CharacterListScreenDestination
import com.skent.rickandmortyv2.ui.DarkGrey2
import com.skent.rickandmortyv2.ui.LightGrey

@Composable
fun RickAndMortyAppBar(navigator: DestinationsNavigator) {
    TopAppBar(content = {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navigator.navigateUp() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = Color.LightGray
                )
            }
            Text(
                text = "Rick and Morty",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = LightGrey
            )
            IconButton(
                onClick = {
                    navigator.navigate(
                        CharacterListScreenDestination
                    ) }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.LightGray
                )
            }
        }
    },
        backgroundColor = DarkGrey2
    )
}