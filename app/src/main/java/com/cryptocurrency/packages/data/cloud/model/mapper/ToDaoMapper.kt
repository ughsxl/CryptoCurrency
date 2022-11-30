package com.cryptocurrency.packages.data.cloud.model.mapper

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.core.copyProperties
import com.cryptocurrency.packages.data.cache.dao.CoinDao
import com.cryptocurrency.packages.data.cache.dao.CoinDetailsDao
import com.cryptocurrency.packages.data.cloud.dto.CoinDetailsDto
import com.cryptocurrency.packages.data.cloud.dto.CoinDto

/**
 * @author Krupko Illa
 * @since 15.11.2022 is created
 */
interface ToDaoMapper : Abstract.Mapper {

	fun map(coinsDto: List<CoinDto>): List<CoinDao>

	fun map(coinDetailsDto: CoinDetailsDto): CoinDetailsDao


	class Base : ToDaoMapper {
		override fun map(coinsDto: List<CoinDto>): List<CoinDao> {
			return coinsDto.map { copyProperties(it) }
		}

		override fun map(coinDetailsDto: CoinDetailsDto): CoinDetailsDao {
			return copyProperties(coinDetailsDto)
		}
	}

}