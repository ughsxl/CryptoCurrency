package com.cryptocurrency.packages.domain

import com.cryptocurrency.packages.data.model.CoinDto

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
interface ApiDataSourceRepository {

	suspend fun getCoinsList(): List<CoinDto>

}