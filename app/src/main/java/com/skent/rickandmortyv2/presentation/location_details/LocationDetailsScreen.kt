package com.skent.rickandmortyv2.presentation.location_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skent.rickandmortyv2.presentation.common_components.RickAndMortyAppBar
import com.skent.rickandmortyv2.presentation.location_details.components.LocationDetailsHeaderSection
import com.skent.rickandmortyv2.presentation.location_details.components.ResidentsListSection
import com.skent.rickandmortyv2.ui.*


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun LocationDetailsScreen(
    navigator: DestinationsNavigator,
    locationID: Int,
    viewModel: LocationDetailsViewModel = hiltViewModel()
) {
    viewModel.getLocationById(locationID)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { RickAndMortyAppBar(navigator = navigator) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGrey),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LocationDetailsHeaderSection(viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            ResidentsListSection(
                navigator = navigator,
                viewModel = viewModel
            )
        }
    }
}