package com.cryptocurrency.packages.data.cache.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Krupko Illa
 * @since 08.11.2022 is created
 */
@Entity(tableName = "CoinTable")
data class CoinDao(
	@PrimaryKey
	val id: String = "unknown",
	@ColumnInfo val name: String = "unknown",
	@ColumnInfo val symbol: String = "unknown",
	@ColumnInfo val rank: Int = 0,

	@ColumnInfo val error: String = ""
)
