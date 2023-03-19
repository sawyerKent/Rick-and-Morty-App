package com.skent.rickandmortyv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.paging.ExperimentalPagingApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.skent.rickandmortyv2.presentation.NavGraphs
import com.skent.rickandmortyv2.ui.RickAndMortyV2Theme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      RickAndMortyV2Theme {
          DestinationsNavHost(navGraph = NavGraphs.root)
      }
    }
  }
}