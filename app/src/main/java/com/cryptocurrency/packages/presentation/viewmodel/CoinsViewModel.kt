package com.cryptocurrency.packages.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.common.ManageResources
import com.cryptocurrency.packages.common.Resource
import com.cryptocurrency.packages.domain.interactor.CoinInteractor
import com.cryptocurrency.packages.presentation.model.CoinUi
import com.cryptocurrency.packages.presentation.view.communication.ListCommunication
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 14:40
 */

class ListViewModel(
	private val interactor: CoinInteractor,
	private val manageResources: ManageResources
) : ViewModel(), ListCommunication {

	private val _coins = MutableLiveData<Resource<List<CoinUi>>>()
	val coins = _coins as LiveData<Resource<List<CoinUi>>>


	init {
		loadCoins()
	}


	override fun loadCoins(): Job = viewModelScope.launch {
		_coins.value = interactor.getCoins()
	}

	override fun sortCoins(by: String): Job = viewModelScope.launch {
		try {
			when (by) {
				manageResources.string(R.string.sort_by_alphabet_title) ->
					_coins.value = Resource.Success(_coins.value!!.data!!.sortedBy {
						it.name
					})

				manageResources.string(R.string.sort_by_rank_title) ->
					_coins.value = Resource.Success(_coins.value!!.data!!.sortedBy {
						it.rank
					})
			}
		} catch (e: NullPointerException) {
			_coins.value = Resource.Error(manageResources.string(R.string.unknownErrorMessage))
		}
	}
}