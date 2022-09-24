package com.cryptocurrency.packages.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 21:57
 */

object RetrofitInstance {
    private const val baseUrl = "https://pro-api.coinmarketcap.com/v1/"
    lateinit var api: ApiRepository

    fun createApi(): ApiRepository? {
        return if (this::api.isInitialized) null
        else {
            api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRepository::class.java)

            api
        }

    }

}