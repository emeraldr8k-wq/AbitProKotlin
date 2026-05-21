package edu.itschool.abitpro.ui.screen.list.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.itschool.abitpro.data.mapper.toHei
import edu.itschool.abitpro.data.network.RetrofitClient
import edu.itschool.abitpro.ui.screen.list.ListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<ListState> = MutableStateFlow(ListState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getData(){
        viewModelScope.launch {
            _uiState.emit(ListState.Loading)
            try {
                val dtos = RetrofitClient.apiService.getUniversities()
                val heis = dtos.map { it.toHei() }
                _uiState.emit(ListState.Content(heis = heis))
            } catch (e: Exception) {
                _uiState.emit(ListState.Error(reason = e.message ?: "Unknown error"))
            }
        }
    }
}