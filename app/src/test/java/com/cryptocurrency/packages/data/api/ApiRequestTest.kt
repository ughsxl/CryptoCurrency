package com.cryptocurrency.packages.data.api

import com.cryptocurrency.packages.data.repository.DataSourceRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @authr Krupko Illa
 * @since 13.10.2022 is created
 */
class ApiDataSourceRepositoryTest {

	@Test
	fun `coins list is not empty`() = runBlocking {
		val apiDataSource = RetrofitInstance.Base(ApiDataSource.Coins::class.java as Class<ApiDataSource>).buildService()
		val apiDataSourceRepository = DataSourceRepositoryImpl(apiDataSource)

		val expected = true
		val actual = apiDataSourceRepository.getCoinsList().isNotEmpty()

		assertEquals(expected, actual)
	}
}