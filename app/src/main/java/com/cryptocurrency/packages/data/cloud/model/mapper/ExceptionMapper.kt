package com.cryptocurrency.packages.data.cloud.model.mapper

import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.core.ManageResources
import com.cryptocurrency.packages.domain.common.CoinException
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownServiceException

/**
 * @author Krupko Illa
 * @since 01.11.2022 is created
 */
interface ExceptionMapper : Abstract.Mapper.Exception<Exception, CoinException> {

	override fun map(exception: Exception): CoinException

	class Base(private val manageResources: ManageResources) : ExceptionMapper {
		override fun map(exception: Exception): CoinException {
			return when (exception) {
				is HttpException ->
					CoinException.ServerError(manageResources.string(R.string.serviceUnavailableErrorMessage))
				is UnknownServiceException ->
					CoinException.ServerError(manageResources.string(R.string.unknownServerErrorMessage))
				is IOException ->
					CoinException.NoConnectionError(manageResources.string(R.string.connectionErrorMessage))
				is IllegalArgumentException ->
					CoinException.WrongArgumentError(manageResources.string(R.string.coinNotFoundErrorMessage))
				is TimeoutCancellationException ->
					CoinException.TimeoutError(manageResources.string(R.string.requestTimeoutErrorMessage))
				is NoSuchFieldException ->
					CoinException.UnknownDatabaseError(manageResources.string(R.string.databaseErrorMessage))
				else ->
					CoinException.UnknownError(manageResources.string(R.string.unknownErrorMessage))
			}
		}
	}

}