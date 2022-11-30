package com.cryptocurrency.packages.presentation.model

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinDetailsModel

/**
 * @author Krupko Illa
 * @since 02.11.2022 is created
 */
sealed class CoinDetailsUi : Abstract.Object.Final() {

	data class Success(val coinDetails: CoinDetailsModel) : CoinDetailsUi()

	data class Fail(val errorMessage: String) : CoinDetailsUi()

}