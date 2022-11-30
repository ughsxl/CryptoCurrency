package com.cryptocurrency.packages.domain.model.mapper

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel
import com.cryptocurrency.packages.domain.common.CoinException
import com.cryptocurrency.packages.presentation.model.CoinsUi

/**
 * @author Krupko Illa
 * @since 31.10.2022 is created
 */
interface CoinsDomainToUiMapper : Abstract.Mapper.Data<List<CoinsModel>, CoinsUi> {

	override fun map(data: List<CoinsModel>): CoinsUi

	override fun map(exception: Exception): CoinsUi

	class Base(
		private val coinExceptionMapper: CoinExceptionMapper
	) : CoinsDomainToUiMapper {

		override fun map(data: List<CoinsModel>): CoinsUi {
			return CoinsUi.Success(data)
		}

		override fun map(exception: Exception): CoinsUi {
			return CoinsUi.Fail(coinExceptionMapper.map(exception as CoinException))
		}
	}

}