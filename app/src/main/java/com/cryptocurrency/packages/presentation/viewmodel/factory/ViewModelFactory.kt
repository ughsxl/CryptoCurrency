package com.cryptocurrency.packages.presentation.viewmodel.factory

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cryptocurrency.packages.data.api.RetrofitInstance
import com.cryptocurrency.packages.data.repository.CoinRepositoryImpl
import com.cryptocurrency.packages.domain.interactor.CoinListInteractor
import com.cryptocurrency.packages.presentation.viewmodel.vm.ListViewModel


/**
 * @author Krupko Illa
 * Created 04.09.2022 at 14:38
 */

fun Fragment.factory() = ViewModelFactory()

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
          ListViewModel::class.java -> {
              val api = RetrofitInstance.createApi() ?: RetrofitInstance.api

              val coinRepository = CoinRepositoryImpl(api)
              val interactor = CoinListInteractor(coinRepository)

              ListViewModel(interactor)
          }
          else -> throw IllegalArgumentException("ViewModel not found")
        }
      return viewModel as T
    }
}