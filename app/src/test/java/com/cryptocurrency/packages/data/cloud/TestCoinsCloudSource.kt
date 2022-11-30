package com.cryptocurrency.packages.data.cloud

import android.content.Context
import androidx.room.Room
import com.cryptocurrency.packages.data.cache.database.CoinDatabase
import com.cryptocurrency.packages.data.cache.database.CoinsCacheService
import com.cryptocurrency.packages.data.cloud.common.CoinDispatcher
import com.cryptocurrency.packages.data.cloud.dto.CoinDetailsDto
import com.cryptocurrency.packages.data.cloud.dto.CoinDto
import com.cryptocurrency.packages.data.cloud.model.CoinDetailsData
import com.cryptocurrency.packages.data.cloud.model.CoinsData
import com.cryptocurrency.packages.data.cloud.model.common.ToModelMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.ToDaoMapper
import com.cryptocurrency.packages.data.cloud.network.CloudModule
import com.cryptocurrency.packages.data.cloud.network.CoinsCloudService
import com.cryptocurrency.packages.data.cloud.network.CoinsCloudSource
import com.cryptocurrency.packages.data.cloud.network.HandleDataRequest
import io.mockk.*
import io.mockk.InternalPlatformDsl.toStr
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownServiceException


/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class TestCoinsCloudSource {

	private val service: CoinsCloudService = mockk()

	private var cacheService: CoinsCacheService = mockk()

	private val handler: HandleDataRequest = createHandler()

	private val modelMapper: ToModelMapper = ToModelMapper.Base()

	private val daoMapper: ToDaoMapper = ToDaoMapper.Base()

	private val source: CoinsCloudSource = createSource()




	private fun createService(): CoinsCloudService {
		val module = mockk<CloudModule>()

		every {
			module.buildService(CoinsCloudService::class.java)
		} returns service

		return module.buildService(CoinsCloudService::class.java)
	}

	private fun createCacheService(): CoinsCacheService {
		val fakeContext = mockk<Context>()

		val database = Room.inMemoryDatabaseBuilder(
			fakeContext, CoinDatabase::class.java
		).build()

		cacheService = database.coinsCacheService()

		return cacheService
	}


	private fun createHandler(): HandleDataRequest {
		return HandleDataRequest.Base(CoinDispatcher.Base().io())
	}

	private fun createSource(): CoinsCloudSource {
		return CoinsCloudSource.Base(
			service = createService(),
			handler = handler,
			modelMapper = modelMapper,
			daoMapper = daoMapper,
			cacheService = createCacheService()
		)
	}


	@Test
	fun `source getCoins() returns valid data`() = runTest {
		coEvery {
			service.getCoins()
		} returns listOf(CoinDto())

		val expected = CoinsData.Success(modelMapper.mapDto(service.getCoins()))
		val actual = source.getCoins()

		assertEquals(expected, actual)

		coVerify {
			service.getCoins()
		}
		confirmVerified(service)
	}

	@Test
	fun `source getCoinById(id) returns valid data`() = runTest {
		coEvery {
			service.getCoinById("btc-bitcoin")
		} returns CoinDetailsDto()

		val expected = CoinDetailsData.Success(
			modelMapper.mapDto(service.getCoinById("btc-bitcoin"))
		)

		val actual = source.getCoinById("btc-bitcoin")

		assertEquals(expected, actual)

		coVerify(exactly = 2) {
			service.getCoinById("btc-bitcoin")
		}
		confirmVerified(service)
	}

	@Test
	fun `source calls service getCoins() with server error returns Fail object`() = runTest {
		val exception = UnknownServiceException()

		coEvery {
			service.getCoins()
		} throws exception

		val expected = CoinsData.Fail(exception).toStr()
		val actual = source.getCoins().toStr()

		assertEquals(expected, actual)

		coVerify(exactly = 1) {
			service.getCoins()
		}
		confirmVerified(service)
	}

	@Test
	fun `source calls service getCoinById(id) with server error returns Fail object`() = runTest {
		val exception = UnknownServiceException()

		coEvery {
			service.getCoinById(any())
		} throws exception

		val expected = CoinDetailsData.Fail(exception).toStr()
		val actual = source.getCoinById("btc-bitcoin").toStr()

		assertEquals(expected, actual)

		coVerify(exactly = 1) {
			service.getCoinById("btc-bitcoin")
		}
		confirmVerified(service)
	}



}