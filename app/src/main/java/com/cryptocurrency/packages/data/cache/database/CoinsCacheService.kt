package com.cryptocurrency.packages.data.cache.database

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cryptocurrency.packages.data.cache.dao.CoinDao
import com.cryptocurrency.packages.data.cache.dao.CoinDetailsDao

/**
 * @author Krupko Illa
 * @since 08.11.2022 is created
 */
@Dao
interface CoinsCacheService {

	@Query("SELECT * FROM CoinTable")
	@Throws(SQLiteException::class)
	suspend fun getCoins(): List<CoinDao>

	@Query("SELECT * FROM CoinDetailsTable WHERE id = :id")
	suspend fun getCoinById(id: String): CoinDetailsDao

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCoins(coins: List<CoinDao>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertCoinDetails(coinDetail: CoinDetailsDao)

}