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
import com.cryptocurrency.packages.data.cloud.model.common.CoinDetailsModel
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel
import com.cryptocurrency.packages.data.cloud.model.common.ToModelMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.CoinDetailsDataToDomainMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.CoinsDataToDomainMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.ExceptionMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.ToDaoMapper
import com.cryptocurrency.packages.data.cloud.network.CloudModule
import com.cryptocurrency.packages.data.cloud.network.CoinsCloudService
import com.cryptocurrency.packages.data.cloud.network.CoinsCloudSource
import com.cryptocurrency.packages.data.cloud.network.HandleDataRequest
import com.cryptocurrency.packages.data.cloud.repository.CoinsCloudRepositoryImpl
import com.cryptocurrency.packages.domain.common.CoinException
import com.cryptocurrency.packages.domain.model.CoinDetailsDomain
import com.cryptocurrency.packages.domain.model.CoinsDomain
import com.cryptocurrency.packages.domain.repository.CoinsCloudRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownServiceException

/**
 * @author Krupko Illa
 * @since 30.10.2022 is created
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class TestCoinsRepository {

	private val coinsMapper: CoinsDataToDomainMapper = mockk()

	private val coinMapper: CoinDetailsDataToDomainMapper = mockk()

	private val exceptionMapper: ExceptionMapper = mockk()

	private val service: CoinsCloudService = mockk()

	private var cacheService: CoinsCacheService = mockk()

	private val modelMapper: ToModelMapper = mockk()

	private val source: CoinsCloudSource = mockk()

	private val repository: CoinsCloudRepository = createRepository(source)


	private fun createCacheService(): CoinsCacheService {
		val fakeContext = mockk<Context>()

 		val database = Room.inMemoryDatabaseBuilder(
			fakeContext, CoinDatabase::class.java
		 ).build()

		cacheService = database.coinsCacheService()

		return cacheService
	}

	private fun createService(): CoinsCloudService {
		val module = mockk<CloudModule>()

		every {
			module.buildService(CoinsCloudService::class.java)
		} returns service

		return module.buildService(CoinsCloudService::class.java)
	}

	private fun createRepository(source: CoinsCloudSource): CoinsCloudRepository {
		return CoinsCloudRepositoryImpl.Base(
			source = source,
			coinsMapper = coinsMapper,
			coinMapper = coinMapper
		)
	}

	@Test
	fun `repository getCoins() returns valid data`() = runTest {
		val coinsModel = listOf(CoinsModel())
		val coinsData = CoinsData.Success(coinsModel)
		val coinsDomain = CoinsDomain.Success(coinsModel)

		coEvery { source.getCoins() } returns coinsData

		every { coinsData.map(coinsMapper) } returns coinsDomain

		val result = repository.getCoins()

		assertEquals(coinsDomain, result)
	}

	@Test
	fun `repository getCoinById(id) returns valid data`() = runTest {
		val coinDetailsModel = CoinDetailsModel()
		val coinDetailsData = CoinDetailsData.Success(coinDetailsModel)
		val coinDetailsDomain = CoinDetailsDomain.Success(coinDetailsModel)

		coEvery { source.getCoinById("btc-bitcoin") } returns coinDetailsData

		every { coinDetailsData.map(coinMapper) } returns coinDetailsDomain

		val result = repository.getCoinById("btc-bitcoin")

		assertEquals(coinDetailsDomain, result)
	}

	@Test
	fun `repository getCoins() error returns coin exception`() = runTest {
		val exception = UnknownServiceException()
		val coinException = CoinException.ServerError("")

		val coinsDataFail = CoinsData.Fail(exception)
		val coinsDomainFail = CoinsDomain.Fail(coinException)

		coEvery { source.getCoins() } returns coinsDataFail

		every { exceptionMapper.map(exception) } returns coinException

		every {
			coinsDataFail.map(coinsMapper)
		} returns CoinsDomain.Fail(exceptionMapper.map(exception))

		val result = repository.getCoins()

		assertEquals(coinsDomainFail, result)
	}

	@Test
	fun `repository getCoinById(id) error returns coin exception`() = runTest {
		val exception = UnknownServiceException()
		val coinException = CoinException.ServerError("")

		val coinsDetailsFail = CoinDetailsData.Fail(exception)
		val coinDetailsDomainFail = CoinDetailsDomain.Fail(coinException)

		coEvery { source.getCoinById("btc-bitcoin") } returns coinsDetailsFail

		every { exceptionMapper.map(exception) } returns coinException

		every {
			coinsDetailsFail.map(coinMapper)
		} returns CoinDetailsDomain.Fail(exceptionMapper.map(exception))

		val result = repository.getCoinById("btc-bitcoin")

		assertEquals(coinDetailsDomainFail, result)
	}

	@Test(expected = TimeoutCancellationException::class)
	fun `repository getCoins() with timeout throws TimeoutCancellationException`() = runTest {
		val timeoutSource = CoinsCloudSource.Base(
			service = createService(),
			handler = HandleDataRequest.Base(CoinDispatcher.Base().io()),
			modelMapper = modelMapper,
			daoMapper = ToDaoMapper.Base(),
			cacheService = createCacheService()
		)

		val timeoutRepository = createRepository(timeoutSource)


		coEvery { service.getCoins() } coAnswers {
			delay(2)
			listOf(CoinDto())
		}

		withTimeout(1) {
			timeoutRepository.getCoins()
		}
	}

	@Test(expected = TimeoutCancellationException::class)
	fun `repository getCoinById(id) with timeout throws TimeoutCancellationException`() = runTest {
		val timeoutSource = CoinsCloudSource.Base(
			service = createService(),
			handler = HandleDataRequest.Base(CoinDispatcher.Base().io()),
			modelMapper = modelMapper,
			daoMapper = mockk(),
			cacheService = createCacheService()
		)

		val timeoutRepository = createRepository(timeoutSource)


		coEvery { service.getCoinById(any()) } coAnswers {
			delay(2)
			CoinDetailsDto()
		}

		withTimeout(1) {
			timeoutRepository.getCoinById("btc-bitcoin")
		}
	}

	@Test
	fun `repository getCoinById(id) with no existing id returns WrongArgumentError`() = runTest {
		val exception = IllegalArgumentException()
		val coinException = CoinException.WrongArgumentError("")

		val coinsDetailsFail = CoinDetailsData.Fail(exception)
		val coinDetailsDomainFail = CoinDetailsDomain.Fail(coinException)

		coEvery { source.getCoinById(" ") } returns coinsDetailsFail

		every { exceptionMapper.map(exception) } returns coinException

		every {
			coinsDetailsFail.map(coinMapper)
		} returns CoinDetailsDomain.Fail(exceptionMapper.map(exception))

		val result = repository.getCoinById(" ")

		assertEquals(coinDetailsDomainFail, result)
	}

}