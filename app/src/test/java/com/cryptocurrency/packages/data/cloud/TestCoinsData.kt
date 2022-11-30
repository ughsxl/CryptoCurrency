package com.cryptocurrency.packages.data.cloud

import com.cryptocurrency.packages.data.cloud.model.CoinsData
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel
import com.cryptocurrency.packages.data.cloud.model.mapper.CoinsDataToDomainMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.ExceptionMapper
import com.cryptocurrency.packages.domain.common.CoinException
import com.cryptocurrency.packages.domain.model.CoinsDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownServiceException

/**
 * @author Krupko Illa
 * @since 31.10.2022 is created
 */
internal class TestCoinsData {

	private val exceptionMapper: ExceptionMapper = mockk()

	private val mapper = CoinsDataToDomainMapper.Base(exceptionMapper)

	@Test
	fun `CoinsData map(data) returns CoinsDomain`() {
		val coins = listOf(CoinsModel())

		val expected: CoinsDomain = CoinsDomain.Success(coins)
		val actual: CoinsDomain = CoinsData.Success(listOf(CoinsModel())).map(mapper)

		assertEquals(expected, actual)
	}

	@Test
	fun `CoinsData map(exception) returns domain exception`() {
		val exception = UnknownServiceException()

		val coinException = CoinException.UnknownError("   Unknown server error\n" +
				"        Swipe down to try again!")

		every {
			exceptionMapper.map(exception)
		} returns coinException


		val expected = CoinsDomain.Fail(coinException)
		val actual = CoinsData.Fail(exception).map(mapper)

		assertEquals(expected, actual)
	}

}