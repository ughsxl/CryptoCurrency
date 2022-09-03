package com.cryptocurrency.packages.data.model.dto

import com.cryptocurrency.packages.data.model.submodel.Quote
import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("cmc_rank")
    val rank: Int,
    val id: Int,
    val name: String,
    val quote: Quote,
    val symbol: String,
    val tags: List<String>
)