package com.cryptocurrency.packages.data.cloud.model

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel
import com.cryptocurrency.packages.data.cloud.model.mapper.CoinsDataToDomainMapper
import com.cryptocurrency.packages.domain.model.CoinsDomain

/**
 * @author Krupko Illa
 * @since 27.10.2022 is created
 */
sealed class CoinsData : Abstract.Object<CoinsDomain, CoinsDataToDomainMapper>() {

	abstract override fun map(mapper: CoinsDataToDomainMapper): CoinsDomain

	data class Success(private val coins: List<CoinsModel>) : CoinsData() {
		override fun map(mapper: CoinsDataToDomainMapper): CoinsDomain {
			return mapper.map(coins)
		}
	}

	data class Fail(private val exception: Exception) : CoinsData() {
		override fun map(mapper: CoinsDataToDomainMapper): CoinsDomain {
			return mapper.map(exception)
		}
	}
}