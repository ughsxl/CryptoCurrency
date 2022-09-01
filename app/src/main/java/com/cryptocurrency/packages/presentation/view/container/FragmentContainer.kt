package com.cryptocurrency.packages.presentation.view.container

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.data.api.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 22:06
 */

class FragmentContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getCoinById("btc-bitcoin")
            } catch (e: IOException) {
                Log.d("coins", "IOException")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.d("coins", "HttpException")
                return@launchWhenCreated
            }
            Log.d("coins", response.toString())
        }
    }
}