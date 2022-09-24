package com.cryptocurrency.packages.presentation.viewmodel.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptocurrency.packages.common.UiText
import com.cryptocurrency.packages.data.model.dto.Resource
import com.cryptocurrency.packages.domain.interactor.CoinListInteractor
import com.cryptocurrency.packages.domain.model.CoinListWrapper
import kotlinx.coroutines.flow.*

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 14:40
 */

class ListViewModel(private val interactor: CoinListInteractor) : ViewModel() {

    private val _coins = MutableStateFlow(CoinListWrapper())
    val coins: StateFlow<CoinListWrapper> = _coins.asStateFlow()


    init {
        getCoins()
    }


    private fun getCoins() {
        interactor.getCoins().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _coins.value = CoinListWrapper(
                        data = result.data ?: emptyList(),
                        message = UiText.coinsAreLoadedMessage,
                        state = Resource.Success()
                    )
                }
                is Resource.Error -> {
                    _coins.value = CoinListWrapper(
                        data = emptyList(),
                        message = result.message ?: UiText.unexpectedErrorMessage,
                        state = Resource.Error()
                    )
                }
                is Resource.Loading -> {
                    _coins.value = CoinListWrapper(
                        data = emptyList(),
                        message = UiText.coinsAreLoadingMessage,
                        state =  Resource.Loading()
                    )

                }
                is Resource.Empty -> {
                    _coins.value = CoinListWrapper(
                        data = emptyList(),
                        message = UiText.coinsAreLoadingMessage,
                        state = Resource.Empty()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}