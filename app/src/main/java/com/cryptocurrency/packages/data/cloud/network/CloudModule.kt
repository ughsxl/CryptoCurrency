package com.cryptocurrency.packages.data.cloud.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
interface CloudModule {

	fun <T> buildService(clazz: Class<T>): T

	class Base : CloudModule {

		override fun <T> buildService(clazz: Class<T>): T {
			val interceptor = HttpLoggingInterceptor().apply {
				setLevel(HttpLoggingInterceptor.Level.BODY)
			}

			val client = OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.build()

			val retrofit = Retrofit.Builder()
				.client(client)
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build()

			return retrofit.create(clazz)
		}

		companion object {
			private const val BASE_URL = "https://api.coinpaprika.com/v1/"
		}

	}

}