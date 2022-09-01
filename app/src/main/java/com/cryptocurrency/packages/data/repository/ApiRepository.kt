package com.cryptocurrency.packages.data.repository

import com.cryptocurrency.packages.data.model.Coin
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 21:33
 */

interface ApiRepository {

    @GET("/v1/coins")
    suspend fun getCoinList(): List<Coin>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): Coin

}