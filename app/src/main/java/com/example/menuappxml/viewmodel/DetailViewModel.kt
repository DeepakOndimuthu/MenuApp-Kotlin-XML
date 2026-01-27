package com.example.menuappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menuappxml.data.repository.MenuRepository
import com.example.menuappxml.ui.common.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {

    private val _state = MutableLiveData<DetailUiState>()
    val state: LiveData<DetailUiState> = _state

    fun loadItem(id: Int) {
        viewModelScope.launch {
            _state.value = DetailUiState.Loading
            try {
                val item = repository.getMenuItemById(id)
                if (item != null) {
                    _state.value = DetailUiState.Success(item)
                } else {
                    _state.value = DetailUiState.Error("Item not found")
                }
            } catch (e: Exception) {
                _state.value = DetailUiState.Error("Something went wrong")
            }
        }
    }
}