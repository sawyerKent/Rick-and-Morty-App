package com.skent.rickandmortyv2.presentation.location_details.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skent.rickandmortyv2.presentation.location_details.LocationDetailsViewModel
import com.skent.rickandmortyv2.ui.White

@Composable
fun LocationDetailsHeaderSection(viewModel: LocationDetailsViewModel) {
    Text(
        text = viewModel.location.name.toString(),
        fontSize = 25.sp,
        color = White
    )
    Text(
        text = viewModel.location.type.toString(),
        fontSize = 20.sp,
        color = White
    )
    Text(
        text = viewModel.location.dimension.toString(),
        fontSize = 20.sp,
        color = White
    )
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = "Residents",
        fontSize = 30.sp,
        color = White
    )
}