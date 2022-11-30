package com.cryptocurrency.packages.data.cloud.network

import com.cryptocurrency.packages.data.cloud.dto.CoinDetailsDto
import com.cryptocurrency.packages.data.cloud.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
interface CoinsCloudService {

	@GET("coins")
	suspend fun getCoins(): List<CoinDto>

	@GET("coins/{coin_id}")
	suspend fun getCoinById(@Path("coin_id") id: String): CoinDetailsDto

}