package com.skent.rickandmortyv2.presentation.character_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.skent.rickandmortyv2.ui.DarkGrey2
import com.skent.rickandmortyv2.ui.LightGrey

@Composable
fun CharactersListAppBar() {
    TopAppBar(content = {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Rick and Morty",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = LightGrey
            )
        }
    },
        backgroundColor = DarkGrey2
    )
}