package com.cryptocurrency.packages.data.cloud.model.mapper

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinDetailsModel
import com.cryptocurrency.packages.domain.model.CoinDetailsDomain

/**
 * @author Krupko Illa
 * @since 27.10.2022 is created
 */
interface CoinDetailsDataToDomainMapper : Abstract.Mapper.Data<CoinDetailsModel, CoinDetailsDomain> {

	override fun map(data: CoinDetailsModel): CoinDetailsDomain

	override fun map(exception: Exception): CoinDetailsDomain


	class Base(
		private val exceptionMapper: ExceptionMapper
	) : CoinDetailsDataToDomainMapper {
		override fun map(data: CoinDetailsModel): CoinDetailsDomain {
			return CoinDetailsDomain.Success(data)
		}

		override fun map(exception: Exception): CoinDetailsDomain {
			return CoinDetailsDomain.Fail(exceptionMapper.map(exception))
		}
	}

}