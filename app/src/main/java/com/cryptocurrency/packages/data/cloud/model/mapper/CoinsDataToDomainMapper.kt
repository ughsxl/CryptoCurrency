package com.cryptocurrency.packages.data.cloud.model.mapper

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel
import com.cryptocurrency.packages.domain.model.CoinsDomain

/**
 * @author Krupko Illa
 * @since 27.10.2022 is created
 */
interface CoinsDataToDomainMapper : Abstract.Mapper.Data<List<CoinsModel>, CoinsDomain> {

	override fun map(data: List<CoinsModel>): CoinsDomain

	override fun map(exception: Exception): CoinsDomain

	class Base(
		private val exceptionMapper: ExceptionMapper
	) : CoinsDataToDomainMapper {

		override fun map(data: List<CoinsModel>): CoinsDomain {
			return CoinsDomain.Success(data)
		}

		override fun map(exception: Exception): CoinsDomain {
			return CoinsDomain.Fail(exceptionMapper.map(exception))
		}

	}

}