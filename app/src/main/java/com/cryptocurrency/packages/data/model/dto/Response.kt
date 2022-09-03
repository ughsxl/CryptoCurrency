package com.cryptocurrency.packages.data.model.dto

import com.cryptocurrency.packages.data.model.submodel.Status

data class Response(
    val data: List<CoinDto>,
    val status: Status
)