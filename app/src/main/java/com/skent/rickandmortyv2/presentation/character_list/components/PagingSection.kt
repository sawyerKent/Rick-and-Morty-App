package com.skent.rickandmortyv2.presentation.character_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skent.rickandmortyv2.presentation.character_list.CharacterListViewModel
import com.skent.rickandmortyv2.ui.White

@Composable
fun PagingSection(
    page: MutableState<Int>,
    viewModel: CharacterListViewModel,
    maxPages: Int = 42,
    minPages: Int = 1
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    page.value = minPages
                    if (viewModel.state.pageNumber != 1) {
                        viewModel.getNewPage("first")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "First Page",
                    modifier = Modifier
                        .rotate(180f)
                        .size(40.dp),
                    tint = White
                )
            }
            IconButton(
                onClick = {
                    if (page.value > minPages) {
                        page.value--
                    }
                    if (viewModel.state.pageNumber != 1) {
                        viewModel.getNewPage("prev")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Previous Page",
                    modifier = Modifier
                        .rotate(180f)
                        .size(32.dp),
                    tint = White
                )
            }
        }
        Text(
            text = "Page ${page.value} of $maxPages",
            fontSize = 22.sp,
            color = White
        )
        Row(horizontalArrangement = Arrangement.Start) {
            IconButton(
                onClick = {
                    if (page.value < maxPages) {
                        page.value++
                    }
                    if (viewModel.state.pageNumber != viewModel.pageCount) {
                        viewModel.getNewPage("next")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Next Page",
                    modifier = Modifier.size(32.dp),
                    tint = White
                )
            }
            IconButton(
                onClick = {
                    page.value = maxPages
                    if (viewModel.state.pageNumber != viewModel.pageCount) {
                        viewModel.getNewPage("last")
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Last Page",
                    modifier = Modifier.size(40.dp),
                    tint = White
                )
            }
        }
    }
}