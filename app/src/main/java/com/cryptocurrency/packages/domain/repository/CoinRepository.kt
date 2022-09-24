package com.cryptocurrency.packages.domain.repository


import com.cryptocurrency.packages.data.model.dto.CoinDto
import com.cryptocurrency.packages.data.model.dto.Resource

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 13:50
 */

interface CoinRepository {

    suspend fun getCoinList(): List<CoinDto>

}