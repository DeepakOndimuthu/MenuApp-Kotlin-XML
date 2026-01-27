package com.example.menuappxml.data.repository

import com.example.menuappxml.data.model.MenuItem
import com.example.menuappxml.data.remote.ApiService
import jakarta.inject.Inject

class MenuRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getMenuItems(): List<MenuItem> {
        return api.getMenuList()
    }

    suspend fun getMenuItemById(id: Int): MenuItem? {
        return api.getMenuList().find { it.id == id }
    }
}