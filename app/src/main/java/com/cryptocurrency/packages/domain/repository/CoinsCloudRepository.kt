package com.cryptocurrency.packages.domain.repository

import com.cryptocurrency.packages.domain.model.CoinDetailsDomain
import com.cryptocurrency.packages.domain.model.CoinsDomain

/**
 * @author Krupko Illa
 * @since 28.10.2022 is created
 */
interface CoinsCloudRepository {

	suspend fun getCoins(): CoinsDomain

	suspend fun getCoinById(id: String): CoinDetailsDomain

}