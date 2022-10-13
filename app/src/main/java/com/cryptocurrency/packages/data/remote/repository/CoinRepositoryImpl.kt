package com.cryptocurrency.packages.data.repository

import android.util.Log
import com.cryptocurrency.packages.BuildConfig
import com.cryptocurrency.packages.data.api.ApiRepository
import com.cryptocurrency.packages.data.model.dto.CoinDto
import com.cryptocurrency.packages.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 21:05
 */

class CoinRepositoryImpl(private val api: ApiRepository) : CoinRepository {
    override suspend fun getCoinList(): List<CoinDto> = withContext(Dispatchers.IO) {
        api.getCoinList(BuildConfig.API_KEY, BuildConfig.COIN_LIMIT).data
    }
}

