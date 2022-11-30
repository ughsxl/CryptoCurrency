package com.cryptocurrency.packages.data.cloud.network

import com.cryptocurrency.packages.data.cache.database.CoinsCacheService
import com.cryptocurrency.packages.data.cloud.model.CoinDetailsData
import com.cryptocurrency.packages.data.cloud.model.CoinsData
import com.cryptocurrency.packages.data.cloud.model.common.ToModelMapper
import com.cryptocurrency.packages.data.cloud.model.mapper.ToDaoMapper
import java.net.UnknownServiceException

/**
 * @author Krupko Illa
 * @since 28.10.2022 is created
 */
interface CoinsCloudSource {

	suspend fun getCoins(): CoinsData

	suspend fun getCoinById(id: String): CoinDetailsData


	class Base(
		private val service: CoinsCloudService,
		private val handler: HandleDataRequest,
		private val modelMapper: ToModelMapper,
		private val daoMapper: ToDaoMapper,
		private val cacheService: CoinsCacheService
	) : CoinsCloudSource {

		override suspend fun getCoins(): CoinsData {
			val onSuccess = suspend {
				val coins = service.getCoins()

				if (coins.isNotEmpty() and coins.all { it.id.isNotBlank() || it.error == ""} ) {
					cacheService.insertCoins(daoMapper.map(coins))
					CoinsData.Success(modelMapper.mapDto(coins))
				}
				else throw UnknownServiceException()
			}

			return handler.handle(onSuccess) { e ->
				CoinsData.Fail(e)
			}
		}


		override suspend fun getCoinById(id: String): CoinDetailsData {
			val onSuccess = suspend {
				val coin = service.getCoinById(id)

				if (coin.id.isNotBlank() || coin.error == "") {
					cacheService.insertCoinDetails(daoMapper.map(coin))
					CoinDetailsData.Success(modelMapper.mapDto(coin))
				}
				else if (coin.error == "id not found")
					throw IllegalArgumentException()
				else
					throw UnknownServiceException()
			}

			return handler.handle(onSuccess) { e ->
				CoinDetailsData.Fail(e)
			}
		}
	}
}