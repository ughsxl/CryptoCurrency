package com.cryptocurrency.packages.data.cloud.dto

import com.cryptocurrency.packages.data.cloud.model.common.Tag
import com.google.gson.annotations.SerializedName

/**
 * @author Krupko Illa
 * @since 14.10.2022 is created
 */
data class CoinDetailsDto(
	val id: String = "unknown",
	val name: String = "unknown",
	val symbol: String = "unknown",
	val rank: Int = 0,
	val description: String = "unknown",
	@SerializedName("logo")
	val logoUrl: String = "https://img.icons8.com/office/344/error-cloud.png",
	val tags: List<Tag> = listOf(
		Tag("Unknown")),
	val team: List<TeamMemberDto> = listOf(TeamMemberDto("unknown", "unknown")),
	@SerializedName("hash_algorithm")
	val hashAlgorithm: String = "unknown",
	@SerializedName("started_at")
	val startedAt: String = "0000-00-0000:00:000",

	val error: String = ""
)
