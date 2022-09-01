package com.cryptocurrency.packages.data.model

import com.google.gson.annotations.SerializedName

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val type: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean
)