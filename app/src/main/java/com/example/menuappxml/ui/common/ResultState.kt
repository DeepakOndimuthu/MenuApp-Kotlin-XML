package com.example.menuappxml.ui.common

import com.example.menuappxml.data.model.MenuItem

sealed class ResultState {
    object Loading : ResultState()
    data class Success(val data: List<MenuItem>) : ResultState()
    data class Error(val message: String) : ResultState()
}