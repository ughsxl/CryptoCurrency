package com.cryptocurrency.packages.domain.model

import com.cryptocurrency.packages.data.model.dto.Resource

/**
 * @author Krupko Illa
 * Created 25.09.2022 at 19:31
 */

data class CoinWrapper(
	val data: Coin? = null,
	val state: Resource<Coin> = Resource.Empty()
)