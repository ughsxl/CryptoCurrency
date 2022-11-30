package com.cryptocurrency.packages.domain.interactor

import com.cryptocurrency.packages.presentation.model.CoinDetailsUi
import com.cryptocurrency.packages.presentation.model.CoinsUi

/**
 * @author Krupko Illa
 * @since 08.11.2022 is created
 */
interface CoinsInteractor {

	suspend fun getCoins(): CoinsUi

	suspend fun getCoinById(id: String): CoinDetailsUi

	fun sortCoins(by: String): CoinsUi

}