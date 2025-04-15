package com.example.health.ViewModel.MainViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _isBottomBarVisible = MutableStateFlow(true)
    val isBottomBarVisible: StateFlow<Boolean> = _isBottomBarVisible

    fun setBottomBarVisibility(isVisible: Boolean) {
        _isBottomBarVisible.value = isVisible
    }
}