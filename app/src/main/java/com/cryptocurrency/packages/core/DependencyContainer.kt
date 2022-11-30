package com.cryptocurrency.packages.core

import android.content.Context
import com.cryptocurrency.packages.data.cache.database.CoinDatabase

/**
 * @author Krupko Illa
 * @since 15.11.2022 is created
 */
class DependencyContainer(private val context: Context) : DependencyProvider {
	override fun provideDatabase() = CoinDatabase.invoke(context)
}