package com.cryptocurrency.packages.data.cloud.model

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinDetailsModel
import com.cryptocurrency.packages.data.cloud.model.mapper.CoinDetailsDataToDomainMapper
import com.cryptocurrency.packages.domain.model.CoinDetailsDomain


/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
sealed class CoinDetailsData : Abstract.Object<CoinDetailsDomain, CoinDetailsDataToDomainMapper>() {

	abstract override fun map(mapper: CoinDetailsDataToDomainMapper): CoinDetailsDomain

	data class Success(private val coinDetails: CoinDetailsModel) : CoinDetailsData() {
		override fun map(mapper: CoinDetailsDataToDomainMapper): CoinDetailsDomain {
			return mapper.map(coinDetails)
		}
	}

	data class Fail(private val exception: Exception) : CoinDetailsData() {
		override fun map(mapper: CoinDetailsDataToDomainMapper): CoinDetailsDomain {
			return mapper.map(exception)
		}
	}

}
