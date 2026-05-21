package edu.itschool.abitpro.ui.screen.list.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding


@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.getData()
    }

    val state by viewModel.uiState.collectAsState()
    
    when (val currentState = state) {
        is edu.itschool.abitpro.ui.screen.list.ListState.Content -> {
            androidx.compose.foundation.lazy.LazyColumn {
                items(currentState.heis.size) { index ->
                    val hei = currentState.heis[index]
                    Text(text = "${hei.name} (${hei.city})", modifier = Modifier.padding(16.dp))
                }
            }
        }
        is edu.itschool.abitpro.ui.screen.list.ListState.Error -> {
            Text(text = "Error: ${currentState.reason}", color = androidx.compose.ui.graphics.Color.Red)
        }
        is edu.itschool.abitpro.ui.screen.list.ListState.Loading -> {
            HomeLoadingState()
        }
    }
}


@Composable
private fun HomeLoadingState() {
    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.size(size = 48.dp)
        )
    }
}