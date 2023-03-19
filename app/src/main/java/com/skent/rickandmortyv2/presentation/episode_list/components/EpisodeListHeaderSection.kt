package com.skent.rickandmortyv2.presentation.episode_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.skent.lib_data.domain.models.character.Result
import com.skent.rickandmortyv2.R
import com.skent.rickandmortyv2.ui.Red
import com.skent.rickandmortyv2.ui.White

@Composable
fun EpisodeListHeaderSection(
    character: Result
 ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (character.image != null) {
            AsyncImage(
                model = character.image,
                contentDescription = "Character Image"
            )
        } else {
            Image(painter = painterResource(
                id = R.drawable.ic_launcher_background),
                contentDescription = "Sample Image",
                modifier = Modifier.size(80.dp)
            )
        }
        Spacer(modifier = Modifier.width(25.dp))
        if (character.name != null) {
            Text(
                text = "${character.name}'s Episodes",
                fontSize = 20.sp,
                color = White
            )
        } else {
            Text(
                text = "Error",
                fontSize = 20.sp,
                color = Red
            )
        }
    }
}