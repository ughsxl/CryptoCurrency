package com.cryptocurrency.packages.domain.model

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel
import com.cryptocurrency.packages.domain.common.CoinException
import com.cryptocurrency.packages.domain.model.mapper.CoinsDomainToUiMapper
import com.cryptocurrency.packages.presentation.model.CoinsUi

/**
 * @author Krupko Illa
 * @since 27.10.2022 is created
 */
sealed class CoinsDomain : Abstract.Object<CoinsUi, CoinsDomainToUiMapper>() {

	abstract override fun map(mapper: CoinsDomainToUiMapper): CoinsUi

	data class Success(private val coins: List<CoinsModel>) : CoinsDomain() {
		override fun map(mapper: CoinsDomainToUiMapper): CoinsUi {
			return mapper.map(coins)
		}
	}

	data class Fail(private val coinException: CoinException) : CoinsDomain() {
		override fun map(mapper: CoinsDomainToUiMapper): CoinsUi {
			return mapper.map(coinException)
		}
	}

}