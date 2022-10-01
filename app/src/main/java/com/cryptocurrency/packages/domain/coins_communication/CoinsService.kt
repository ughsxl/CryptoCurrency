package com.cryptocurrency.packages.domain.coins_communication

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptocurrency.packages.domain.model.Coin
import com.cryptocurrency.packages.presentation.view.adapter.CoinAdapter


/**
 * @author Krupko Illa
 * Created 25.09.2022 at 21:22
 */

class CoinsService {
	private var coins = listOf<Coin>()
	private val adapter = CoinAdapter()

	interface InitService {

		fun setCoins(coins: List<Coin>)

		fun initAdapter(recyclerView: RecyclerView, context: Context)

	}

	interface ServiceCommunication {

		fun sortCoins(by: String)

		fun pushCoins(coins: List<Coin>)

	}

	inner class Init : InitService {

		override fun setCoins(coins: List<Coin>) {
			this@CoinsService.coins = coins
		}

		override fun initAdapter(recyclerView: RecyclerView, context: Context) {
			recyclerView.layoutManager = LinearLayoutManager(context)
			recyclerView.adapter = adapter

			Communication().pushCoins(coins)
		}

	}

	inner class Communication : ServiceCommunication {

		override fun sortCoins(by: String) {
			when (by) {
				"Sort by Alphabet" -> coins = coins.sortedBy { it.name }
				"Sort by Price" -> coins = coins.sortedByDescending { it.price }
				"Sort by Rank" -> coins = coins.sortedBy { it.rank }
			}

			pushCoins(coins)
			Init().setCoins(coins)
		}

		override fun pushCoins(coins: List<Coin>) {
			adapter.setCoinsList(coins)
		}

	}

}