package com.cryptocurrency.packages.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cryptocurrency.packages.domain.model.Coin

/**
 * @author Krupko Illa
 * Created 01.10.2022 at 17:24
 */

class CoinsDiffUtilCallback(
	private val oldList: List<Coin>,
	private val newList: List<Coin>
) : DiffUtil.Callback() {

	override fun getOldListSize(): Int = oldList.size

	override fun getNewListSize(): Int = newList.size

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val oldCoin = oldList[oldItemPosition]
		val newCoin = oldList[newItemPosition]

		return oldCoin.id == newCoin.id
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		val oldCoin = oldList[oldItemPosition]
		val newCoin = oldList[newItemPosition]

		return oldCoin == newCoin
	}

}