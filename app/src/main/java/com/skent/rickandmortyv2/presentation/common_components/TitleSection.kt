package com.skent.rickandmortyv2.presentation.common_components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.skent.rickandmortyv2.ui.Red
import com.skent.rickandmortyv2.ui.White

@Composable
fun TitleSection(title: String) {
    if (title != "Error") {
        Text(
            modifier = Modifier.fillMaxHeight(0.1f),
            text = title,
            fontSize = 30.sp,
            color = White
        )
    } else {
        Text(
            modifier = Modifier.fillMaxHeight(0.1f),
            text = title,
            fontSize = 30.sp,
            color = Red
        )
    }

}