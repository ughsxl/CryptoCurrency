package com.cryptocurrency.packages.data.api

import com.cryptocurrency.packages.data.repository.ApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 21:57
 */

object RetrofitInstance {
    private const val baseUrl = "https://api.coinpaprika.com/v1/"

    val api: ApiRepository by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRepository::class.java)
    }
}