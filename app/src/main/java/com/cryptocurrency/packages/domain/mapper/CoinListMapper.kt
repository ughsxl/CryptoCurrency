package com.cryptocurrency.packages.domain.mapper

import com.cryptocurrency.packages.data.model.dto.CoinDto
import com.cryptocurrency.packages.domain.model.Coin

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 17:24
 */

class CoinListMapper : Mapper<List<CoinDto>, List<Coin>> {
    override fun map(model: List<CoinDto>): List<Coin> {
        return model.map { CoinMapper().map(it) }
    }
}