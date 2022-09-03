package com.cryptocurrency.packages.domain.repository

import android.content.Context
import com.cryptocurrency.packages.data.model.dto.CoinDto

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 13:50
 */

interface CoinRepository {

    suspend fun getCoinList(context: Context): List<CoinDto>?

}