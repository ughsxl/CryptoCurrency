package com.cryptocurrency.packages.data.cache.database

import com.cryptocurrency.packages.data.cloud.model.CoinDetailsData
import com.cryptocurrency.packages.data.cloud.model.CoinsData
import com.cryptocurrency.packages.data.cloud.model.common.ToModelMapper
import com.cryptocurrency.packages.data.cloud.network.HandleDataRequest

/**
 * @author Krupko Illa
 * @since 14.11.2022 is created
 */
interface CoinsCacheSource {

	suspend fun getCoins(): CoinsData

	suspend fun getCoinById(id: String): CoinDetailsData


	class Base(
		private val service: CoinsCacheService,
		private val handler: HandleDataRequest,
		private val modelMapper: ToModelMapper
	) : CoinsCacheSource {
		override suspend fun getCoins(): CoinsData {
			val onSuccess = suspend {
				val coins = service.getCoins()

				CoinsData.Success(modelMapper.mapDao(coins))
			}

			return handler.handle(onSuccess) { e ->
				CoinsData.Fail(e)
			}
		}

		override suspend fun getCoinById(id: String): CoinDetailsData {
			val onSuccess = suspend {
				val coinDetails = service.getCoinById(id)

				CoinDetailsData.Success(modelMapper.mapDao(coinDetails))
			}

			return handler.handle(onSuccess) { e ->
				CoinDetailsData.Fail(e)
			}
		}
	}

}