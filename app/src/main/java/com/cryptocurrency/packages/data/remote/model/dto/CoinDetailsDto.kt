package com.cryptocurrency.packages.data.remote.model.dto

import com.cryptocurrency.packages.data.remote.model.submodel.Quote
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: Int,
    val rank: Int,
    val name: String,
    val quote: Quote,
    val symbol: String,
    val tags: List<String>
)