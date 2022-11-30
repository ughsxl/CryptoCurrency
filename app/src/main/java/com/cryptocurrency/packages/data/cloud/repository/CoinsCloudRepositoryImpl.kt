package com.cryptocurrency.packages.data.cloud.repository

import com.cryptocurrency.packages.data.cloud.model.mapper.CoinDetailsDataToDomainMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.CoinsDataToDomainMapper
import com.cryptocurrency.packages.data.cloud.network.CoinsCloudSource
import com.cryptocurrency.packages.domain.model.CoinDetailsDomain
import com.cryptocurrency.packages.domain.model.CoinsDomain
import com.cryptocurrency.packages.domain.repository.CoinsCloudRepository


/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
interface CoinsCloudRepositoryImpl : CoinsCloudRepository {

	override suspend fun getCoins(): CoinsDomain

	override suspend fun getCoinById(id: String): CoinDetailsDomain

	class Base(
		private val source: CoinsCloudSource,
		private val coinsMapper: CoinsDataToDomainMapper,
		private val coinMapper: CoinDetailsDataToDomainMapper
	) : CoinsCloudRepositoryImpl {

		override suspend fun getCoins(): CoinsDomain {
			return source.getCoins().map(coinsMapper)
		}

		override suspend fun getCoinById(id: String): CoinDetailsDomain {
			return source.getCoinById(id).map(coinMapper)
		}
	}

}