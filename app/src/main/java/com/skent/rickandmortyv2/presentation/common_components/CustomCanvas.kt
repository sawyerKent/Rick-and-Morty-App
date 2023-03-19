package com.skent.rickandmortyv2.presentation.common_components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import com.skent.rickandmortyv2.ui.DarkGrey2

@Composable
fun CustomCanvas(modifier: Modifier) {
    Canvas(modifier = modifier) {
        val clipPath = Path().apply {
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        clipPath(clipPath) {
            drawRoundRect(
                color = DarkGrey2,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx()) //cornerRadius
            )
        }
    }
}