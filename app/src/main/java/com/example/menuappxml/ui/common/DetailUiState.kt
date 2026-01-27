package com.example.menuappxml.ui.common

import com.example.menuappxml.data.model.MenuItem

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val item: MenuItem) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}