package edu.itschool.abitpro.ui.screen.list.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.itschool.abitpro.ui.screen.list.ListState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<ListState> = MutableStateFlow(ListState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getData(){
        viewModelScope.launch {
            _uiState.emit(ListState.Loading)
            delay(2000L)
            _uiState.emit(  ListState.Error(reason = "User error"))
        }
    }

}