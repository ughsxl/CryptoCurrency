package com.cryptocurrency.packages.data.repository

import android.content.Context
import android.widget.Toast
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.data.api.ApiRepository
import com.cryptocurrency.packages.data.model.dto.CoinDto
import com.cryptocurrency.packages.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.io.PrintStream
import kotlin.coroutines.coroutineContext

/**
 * @author Krupko Illa
 * Created 02.09.2022 at 21:05
 */

class CoinRepositoryImpl(private val api: ApiRepository) : CoinRepository {
    override suspend fun getCoinList(context: Context): List<CoinDto>? = withContext(Dispatchers.Main) {
            try {
                val response = api.getCoinList("0bd7b2fc-e213-4f0f-a485-33964f95d2e5", 200)

                if (response.status.error_code != 0)
                    return@withContext response.data
                else {
                    Toast.makeText(context, response.status.error_message ?:
                    context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()

                    return@withContext null
                }
            } catch (e: IOException) {
                Toast.makeText(context, context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(context, e.code().toString(), Toast.LENGTH_SHORT).show()
            }
            return@withContext null
        }
    }
