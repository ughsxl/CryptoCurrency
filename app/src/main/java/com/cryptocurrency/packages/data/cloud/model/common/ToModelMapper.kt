package com.cryptocurrency.packages.data.cloud.model.common

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.core.copyProperties
import com.cryptocurrency.packages.data.cache.dao.CoinDao
import com.cryptocurrency.packages.data.cache.dao.CoinDetailsDao
import com.cryptocurrency.packages.data.cloud.dto.CoinDetailsDto
import com.cryptocurrency.packages.data.cloud.dto.CoinDto


/**
 * @author Krupko Illa
 * @since 14.11.2022 is created
 */
interface ToModelMapper : Abstract.Mapper.Empty {

	fun mapDto(data: List<CoinDto>): List<CoinsModel>

	fun mapDto(data: CoinDetailsDto): CoinDetailsModel

	fun mapDao(data: List<CoinDao>): List<CoinsModel>

	fun mapDao(data: CoinDetailsDao): CoinDetailsModel


	class Base : ToModelMapper {

		override fun mapDto(data: List<CoinDto>): List<CoinsModel> = data.map { copyProperties(it) }

		override fun mapDto(data: CoinDetailsDto): CoinDetailsModel = copyProperties(data)

		override fun mapDao(data: List<CoinDao>): List<CoinsModel> = data.map { copyProperties(it) }

		override fun mapDao(data: CoinDetailsDao): CoinDetailsModel = copyProperties(data)

	}

}