package com.cryptocurrency.packages.data.cloud.dto

/**
 * @author Krupko Illa
 * @since 27.10.2022 is created
 */
data class CoinDto(
	val id: String = "unknown",
	val name: String = "unknown",
	val symbol: String = "unknown",
	val rank: Int = 0,

	val error: String = ""
)