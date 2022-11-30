package com.cryptocurrency.packages.presentation.model

import com.cryptocurrency.packages.core.Abstract
import com.cryptocurrency.packages.data.cloud.model.common.CoinsModel

/**
 * @author Krupko Illa
 * @since 31.10.2022 is created
 */
sealed class CoinsUi : Abstract.Object.Final() {

	data class Success(val coins: List<CoinsModel>) : CoinsUi()

	data class Fail(val errorMessage: String) : CoinsUi()

}