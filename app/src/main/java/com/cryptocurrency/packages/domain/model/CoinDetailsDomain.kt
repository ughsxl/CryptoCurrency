package com.cryptocurrency.packages.domain.model

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinDetailsModel
import com.cryptocurrency.packages.domain.common.CoinException
import com.cryptocurrency.packages.domain.model.mapper.CoinDetailsDomainToUiMapper
import com.cryptocurrency.packages.presentation.model.CoinDetailsUi

/**
 * @author Krupko Illa
 * @since 26.10.2022 is created
 */
sealed class CoinDetailsDomain  : Abstract.Object<CoinDetailsUi, CoinDetailsDomainToUiMapper>() {

	abstract override fun map(mapper: CoinDetailsDomainToUiMapper): CoinDetailsUi

	data class Success(private val coin: CoinDetailsModel) : CoinDetailsDomain() {
		override fun map(mapper: CoinDetailsDomainToUiMapper): CoinDetailsUi {
			return mapper.map(coin)
		}
	}

	data class Fail(private val coinException: CoinException) : CoinDetailsDomain() {
		override fun map(mapper: CoinDetailsDomainToUiMapper): CoinDetailsUi {
			return mapper.map(coinException)
		}
	}

}
