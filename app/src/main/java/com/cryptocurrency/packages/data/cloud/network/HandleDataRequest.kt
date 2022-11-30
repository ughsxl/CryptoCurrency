package com.cryptocurrency.packages.data.cloud.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

/**
 * @author Krupko Illa
 * @since 20.10.2022 is created
 */
interface HandleDataRequest {

	suspend fun <R : Any> handle(onSuccess: suspend () -> R, onError: suspend (Exception) -> R): R

	class Base(private val dispatcher: CoroutineDispatcher) : HandleDataRequest {
		override suspend fun <R : Any> handle(
			onSuccess: suspend () -> R,
			onError: suspend (Exception) -> R,
		) = withContext(dispatcher) {
				try {
					withTimeout(100000L) {
						onSuccess.invoke()
					}
				} catch (e: Exception) {
					onError.invoke(e)
				}
			}


	}

}
