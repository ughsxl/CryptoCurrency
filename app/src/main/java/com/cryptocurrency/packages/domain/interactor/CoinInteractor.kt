package com.cryptocurrency.packages.domain.interactor


import com.cryptocurrency.packages.common.NetworkExceptionHandler
import com.cryptocurrency.packages.common.Resource
import com.cryptocurrency.packages.domain.mapper.CoinListMapper
import com.cryptocurrency.packages.domain.mapper.CoinMapper
import com.cryptocurrency.packages.domain.repository.CoinRepository
import com.cryptocurrency.packages.presentation.model.CoinDetailsUi


/**
 * @author Krupko Illa
 * Created 04.09.2022 at 12:53
 */

interface CoinListInteractor {

    suspend fun getCoins(): Resource<List<CoinDetailsUi>>

    suspend fun getCoinById(id: String): Resource<CoinDetailsUi>

    class Base(
        private val repository: CoinRepository,
        private val listMapper: CoinListMapper,
        private val coinMapper: CoinMapper,
        private val networkExceptionHandler: NetworkExceptionHandler,
    ) : CoinListInteractor {

        override suspend fun getCoins() = networkExceptionHandler.handle {
            listMapper.map(repository.getCoinList().subList(0, 500))
        }

        override suspend fun getCoinById(id: String) = networkExceptionHandler.handle {
            coinMapper.map(repository.getCoinById(id))
        }

    }
}
