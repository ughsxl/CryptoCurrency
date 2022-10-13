package com.cryptocurrency.packages.presentation.viewmodel.factory

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cryptocurrency.packages.common.ManageResources
import com.cryptocurrency.packages.common.NetworkExceptionHandler
import com.cryptocurrency.packages.data.remote.api.RetrofitInstance
import com.cryptocurrency.packages.data.remote.repository.CoinRepositoryImpl
import com.cryptocurrency.packages.domain.interactor.CoinInteractor
import com.cryptocurrency.packages.presentation.viewmodel.vm.ListViewModel


/**
 * @author Krupko Illa
 * Created 04.09.2022 at 14:38
 */

fun Fragment.factory(context: Context) = ViewModelFactory(context)

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		val viewModel = when (modelClass) {
			ListViewModel::class.java -> {
				val coinRepository = CoinRepositoryImpl(RetrofitInstance.createApi())
				val manageResources = ManageResources.Base(context)
				val networkExceptionHandler = NetworkExceptionHandler.Base(manageResources)

				val interactor = CoinInteractor(
					coinRepository, networkExceptionHandler, manageResources
				)

				ListViewModel(interactor)
			}
			else -> throw IllegalArgumentException("ViewModel not found")
		}


		return viewModel as T
	}

}

