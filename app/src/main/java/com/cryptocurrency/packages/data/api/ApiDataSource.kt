package com.cryptocurrency.packages.data.api

import com.cryptocurrency.packages.data.model.CoinDto
import retrofit2.http.GET

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
interface ApiDataSource {

	suspend fun getCoinsList(): List<CoinDto>

	interface Coins : ApiDataSource {

		@GET("coins")
		override suspend fun getCoinsList(): List<CoinDto>

	}

}