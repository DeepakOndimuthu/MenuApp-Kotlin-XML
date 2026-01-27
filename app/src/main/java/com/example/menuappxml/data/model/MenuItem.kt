package com.example.menuappxml.data.model

import com.google.gson.annotations.SerializedName

data class MenuItem(
    val id: Int,
    val title: String,
    val description: String,
    @SerializedName("image")
    val imageUrl: String
)