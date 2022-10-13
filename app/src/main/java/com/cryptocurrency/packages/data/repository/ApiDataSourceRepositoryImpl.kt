package com.cryptocurrency.packages.data.repository

import com.cryptocurrency.packages.data.api.ApiDataSource
import com.cryptocurrency.packages.data.model.CoinDto
import com.cryptocurrency.packages.domain.ApiDataSourceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
class DataSourceRepositoryImpl(
	private val apiDataSource: ApiDataSource
) : ApiDataSourceRepository {

	override suspend fun getCoinsList(): List<CoinDto> = withContext(Dispatchers.IO) {
		apiDataSource.getCoinsList()
	}

}