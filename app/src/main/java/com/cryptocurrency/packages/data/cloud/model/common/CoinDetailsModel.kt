package com.cryptocurrency.packages.data.cloud.model.common

import com.cryptocurrency.packages.data.cloud.dto.TeamMemberDto

/**
 * @author Krupko Illa
 * @since 14.11.2022 is created
 */
data class CoinDetailsModel(
	val id: String = "unknown",
	val name: String = "unknown",
	val symbol: String = "unknown",
	val rank: Int = 0,
	val description: String = "unknown",
	val logoUrl: String = "unknown",
	val tagDtos: List<Tag> = listOf(
		Tag("unknown")),
	val team: List<TeamMemberDto> = listOf(TeamMemberDto("unknown", "unknown")),
	val hashAlgorithm: String = "unknown",
	val startedAt: String = "00:00:00",

	val error: String = ""
)
