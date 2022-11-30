package com.cryptocurrency.packages.domain.model.mapper

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinDetailsModel
import com.cryptocurrency.packages.domain.common.CoinException
import com.cryptocurrency.packages.presentation.model.CoinDetailsUi

/**
 * @author Krupko Illa
 * @since 06.11.2022 is created
 */
interface CoinDetailsDomainToUiMapper : Abstract.Mapper.Data<CoinDetailsModel, CoinDetailsUi> {

	override fun map(data: CoinDetailsModel): CoinDetailsUi

	override fun map(exception: Exception): CoinDetailsUi

	class Base(
		private val coinExceptionMapper: CoinExceptionMapper
	) : CoinDetailsDomainToUiMapper {

		override fun map(data: CoinDetailsModel): CoinDetailsUi {
			return CoinDetailsUi.Success(data)
		}

		override fun map(exception: Exception): CoinDetailsUi {
			return CoinDetailsUi.Fail(coinExceptionMapper.map(exception as CoinException))
		}
	}
}