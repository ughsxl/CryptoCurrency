package com.cryptocurrency.packages.core

import com.cryptocurrency.packages.data.cache.database.CoinDatabase

/**
 * @author Krupko Illa
 * @since 29.10.2022 is created
 */
interface DependencyProvider {
	fun provideDatabase(): CoinDatabase
}