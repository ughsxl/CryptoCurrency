package com.cryptocurrency.packages.data.cloud.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Krupko Illa
 * @since 20.10.2022 is created
 */
interface CoinDispatcher  {

	fun io(): CoroutineDispatcher
	fun ui(): CoroutineDispatcher

	class Base : CoinDispatcher {
		override fun io(): CoroutineDispatcher = Dispatchers.IO
		override fun ui(): CoroutineDispatcher = Dispatchers.Main
	}
}