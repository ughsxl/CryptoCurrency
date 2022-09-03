package com.cryptocurrency.packages.domain.mapper

import com.cryptocurrency.packages.data.model.dto.CoinDto
import com.cryptocurrency.packages.domain.model.Coin

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 17:11
 */

class CoinMapper : Mapper<CoinDto, Coin> {
    override fun map(model: CoinDto): Coin = Coin (
            id = model.id,
            name = model.name,
            symbol = model.symbol,
            rank = model.rank,
            tags = model.tags,
            value = "${model.quote.USD.price}$"
        )
}