package com.skent.rickandmortyv2.presentation.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skent.rickandmortyv2.ui.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.skent.rickandmortyv2.presentation.character_list.components.CharacterListSection
import com.skent.rickandmortyv2.presentation.character_list.components.CharactersListAppBar
import com.skent.rickandmortyv2.presentation.character_list.components.PagingSection

var page = mutableStateOf(1)
val maxPages = 42
val minPages = 1


@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun CharacterListScreen(
    navigator: DestinationsNavigator,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CharactersListAppBar() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(DarkGrey)
        ) {
            if (viewModel.state.isLoading || !viewModel.start) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkGrey),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(40.dp))
                    PagingSection(
                        page = page,
                        viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CharacterListSection(
                        navigator = navigator,
                        state = viewModel.state
                    )
                }
            }
        }
    }
}