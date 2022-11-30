package com.cryptocurrency.packages.data.cache.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cryptocurrency.packages.data.cloud.model.common.Tag

/**
 * @author Krupko Illa
 * @since 08.11.2022 is created
 */
@Entity(tableName = "CoinDetailsTable")
data class CoinDetailsDao(
	@PrimaryKey
	@ColumnInfo val id: String = "unknown",
	@ColumnInfo val name: String = "unknown",
	@ColumnInfo val symbol: String = "unknown",
	@ColumnInfo val rank: Int = 0,
	@ColumnInfo val description: String = "unknown",
	@ColumnInfo val logoUrl: String = "https://img.icons8.com/office/344/error-cloud.png",
	@ColumnInfo val team: List<TeamMemberDao>,
	@ColumnInfo val tag: List<Tag> = listOf(Tag("unknown")),
	@ColumnInfo val hashAlgorithm: String = "unknown",
	@ColumnInfo val startedAt: String = "0000-00-0000:00:000",

	@ColumnInfo val error: String = ""
)
