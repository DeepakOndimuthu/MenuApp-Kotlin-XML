package com.example.menuappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menuappxml.data.repository.MenuRepository
import com.example.menuappxml.ui.common.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    private val _state = MutableLiveData<ResultState>()
    val state: LiveData<ResultState> = _state

    init {
        loadMenu()
    }

    private fun loadMenu() {
        viewModelScope.launch {
            _state.value = ResultState.Loading
            try {
                val list = repository.getMenuItems()
                _state.value = ResultState.Success(list)
            } catch (e: Exception) {
                _state.value = ResultState.Error("Network error")
            }
        }
    }
}
