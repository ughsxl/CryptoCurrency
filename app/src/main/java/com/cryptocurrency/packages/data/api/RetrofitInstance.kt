package com.cryptocurrency.packages.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
interface RetrofitInstance {

	fun buildService(): ApiDataSource

	class Base(
		private val apiDataSource: Class<ApiDataSource>
	) : RetrofitInstance {
		override fun buildService(): ApiDataSource {
			val retrofit = Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build()

			return retrofit.create(apiDataSource)
		}

		companion object {
			private const val BASE_URL = "https://api.coinpaprika.com/v1/"
		}

	}

}