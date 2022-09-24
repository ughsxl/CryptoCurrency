package com.cryptocurrency.packages.domain.interactor


import android.util.Log
import com.cryptocurrency.packages.common.ExceptionHandler
import com.cryptocurrency.packages.common.UiText
import com.cryptocurrency.packages.data.model.dto.Resource
import com.cryptocurrency.packages.data.repository.CoinRepositoryImpl
import com.cryptocurrency.packages.domain.mapper.CoinListMapper
import com.cryptocurrency.packages.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.job
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.coroutineContext


/**
 * @author Krupko Illa
 * Created 04.09.2022 at 12:53
 */

class CoinListInteractor(private val repository: CoinRepositoryImpl) {
    fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Empty())
            emit(Resource.Loading())
            val coins = CoinListMapper().map(repository.getCoinList())
            emit(Resource.Success(data = coins))
        } catch (e: HttpException) {
            ExceptionHandler(e).Handle {
                emit(Resource.Error(it.localizedMessage ?: UiText.unexpectedErrorMessage))
            }
        } catch (e: IOException) {
            ExceptionHandler(e).Handle {
                emit(Resource.Error(it.localizedMessage ?: UiText.connectionErrorMessage))
            }
        }
    }
}
