package com.cryptocurrency.packages.data.api

import com.cryptocurrency.packages.data.model.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 21:33
 */

interface ApiRepository {

    @GET("cryptocurrency/listings/latest")
    suspend fun getCoinList(@Query("CMC_PRO_API_KEY") apiKey: String, @Query("limit") listLimit: Int): Response

}