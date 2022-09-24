package com.cryptocurrency.packages.domain.model

import com.cryptocurrency.packages.data.model.dto.Resource

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 13:27
 */

data class CoinListWrapper(
    var data: List<Coin> = emptyList(),
    var message: String = "",
    var state: Resource<List<Coin>> = Resource.Empty()
)