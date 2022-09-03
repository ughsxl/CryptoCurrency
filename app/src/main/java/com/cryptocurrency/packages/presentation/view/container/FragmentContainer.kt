package com.cryptocurrency.packages.presentation.view.container

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.data.api.RetrofitInstance
import com.cryptocurrency.packages.data.repository.CoinRepositoryImpl
import com.cryptocurrency.packages.domain.mapper.CoinListMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import kotlin.concurrent.thread

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 22:06
 */

class FragmentContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
    }
}