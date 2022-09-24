package com.cryptocurrency.packages.domain.model

import com.cryptocurrency.packages.data.model.submodel.Quote


/**
 * @author Krupko Illa
 * Created 02.09.2022 at 14:22
 */

data class Coin(
    val id: Int,
    val name: String,
    val symbol: String,
    val rank: String,
    val tags: List<String>,
    val price: String
)