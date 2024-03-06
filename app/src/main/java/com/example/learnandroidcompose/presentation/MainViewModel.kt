package com.example.learnandroidcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnandroidcompose.domain.XResolver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainState(
    val a: String = "",
    val b: String = "",
    val x: String = "?",
)

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val xResolver = XResolver()

    private val _uiState = MutableStateFlow(MainState())
    val uiState = _uiState.asStateFlow()

    fun onAValueChanged(a: String) {
        _uiState.update { it.copy(a = a) }
    }

    fun onBValueChanged(b: String) {
        _uiState.update { it.copy(b = b) }
    }

    fun resolveX(a: Double, b: Double) {
        _uiState.update { it.copy(x = xResolver.resolveX(a, b)) }
    }
}