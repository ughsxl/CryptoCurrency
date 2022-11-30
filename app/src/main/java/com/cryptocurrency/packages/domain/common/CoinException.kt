package com.cryptocurrency.packages.domain.common

/**
 * @author Krupko Illa
 * @since 31.10.2022 is created
 */
sealed class CoinException(override val message: String) : Exception(message) {

	class NoConnectionError(errorMessage: String) : CoinException(errorMessage)

	class ServerError(errorMessage: String) : CoinException(errorMessage)

	class WrongArgumentError(errorMessage: String) : CoinException(errorMessage)

	class UnknownError(errorMessage: String) : CoinException(errorMessage)

	class TimeoutError(errorMessage: String) : CoinException(errorMessage)

	class UnknownDatabaseError(errorMessage: String) : CoinException(errorMessage)

}