package com.example.menuappxml.data.remote

import com.example.menuappxml.data.model.MenuItem
import retrofit2.http.GET

interface ApiService {

    @GET("products?limit=5")
    suspend fun getMenuList(): List<MenuItem>
}