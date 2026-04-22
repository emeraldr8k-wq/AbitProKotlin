package edu.itschool.abitpro.ui.screen.list.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
//    viewModel: HomeViewModel = (viewModel())

) {

    Text(
        "HomeScreen"
    )
//    val state by viewModel.uiState.collectAsState()
//    when (state) {
//        is ListState.Content -> TODO()
//        is ListState.Error -> TODO()
//        is ListState.Loading -> TODO()
//    }


}


@Composable
private fun HomeLoadingState() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 48.dp)
        )
    }
}