package com.cryptocurrency.packages.data.cloud.model.common

/**
 * @author Krupko Illa
 * @since 14.11.2022 is created
 */
data class CoinsModel (
	val id: String = "unknown",
	val name: String = "unknown",
	val symbol: String = "unknown",
	val rank: Int = 0,

	val error: String = ""
)