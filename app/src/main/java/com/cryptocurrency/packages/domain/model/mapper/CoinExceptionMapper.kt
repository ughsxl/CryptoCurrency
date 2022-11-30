package com.cryptocurrency.packages.domain.model.mapper

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.domain.common.CoinException

/**
 * @author Krupko Illa
 * @since 07.11.2022 is created
 */
interface CoinExceptionMapper : Abstract.Mapper.Exception<CoinException, String> {

	override fun map(exception: CoinException): String

	class Base : CoinExceptionMapper {
		override fun map(exception: CoinException): String {
			return exception.message
		}
	}

}