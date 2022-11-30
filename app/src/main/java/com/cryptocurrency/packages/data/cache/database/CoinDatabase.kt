package com.cryptocurrency.packages.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cryptocurrency.packages.data.cache.dao.CoinDao
import com.cryptocurrency.packages.data.cache.dao.CoinDetailsDao
import com.cryptocurrency.packages.data.cache.dao.CoinTypeConverter

/**
 * @author Krupko Illa
 * @since 08.11.2022 is created
 */
@Database(entities = [CoinDao::class, CoinDetailsDao::class], version = 1)
@TypeConverters(CoinTypeConverter::class)
abstract class CoinDatabase : RoomDatabase() {

	abstract fun coinsCacheService(): CoinsCacheService

	companion object {

		operator fun invoke(context: Context) = createDatabase(context)

		private fun createDatabase(context: Context): CoinDatabase =
			Room.databaseBuilder(
				context, CoinDatabase::class.java, "coins_database"
			).build()
	}
}