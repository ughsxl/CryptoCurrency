package com.cryptocurrency.packages.data.cache

import android.content.Context
import com.cryptocurrency.packages.core.DependencyContainer
import com.cryptocurrency.packages.data.cache.database.CoinsCacheService
import com.cryptocurrency.packages.data.cache.database.CoinsCacheSource
import com.cryptocurrency.packages.data.cloud.common.CoinDispatcher
import com.cryptocurrency.packages.data.cloud.model.common.ToModelMapper
import com.cryptocurrency.packages.data.cloud.network.HandleDataRequest
import io.mockk.every
import io.mockk.mockk

/**
 * @author Krupko Illa
 * @since 15.11.2022 is created
 */
internal class TestCoinsCacheSource {

	private val service: CoinsCacheService = createService()

	private val handler: HandleDataRequest = HandleDataRequest.Base(CoinDispatcher.Base().io())

	private val modelMapper: ToModelMapper = ToModelMapper.Base()

	private val source: CoinsCacheSource = createSource()

	private fun createService(): CoinsCacheService {
		val service = mockk<CoinsCacheService>()
		val fakeContext = mockk<Context>()

		every {
			DependencyContainer(fakeContext).provideDatabase().coinsCacheService()
		} returns service

		return DependencyContainer(fakeContext).provideDatabase().coinsCacheService()
	}

	private fun createSource(): CoinsCacheSource {
		return CoinsCacheSource.Base(
			service, handler, modelMapper
		)
	}


//	@Test
//	fun `service getCoins() returns valid data`() = runTest {
//		println(service.getCoins())
//	}

}