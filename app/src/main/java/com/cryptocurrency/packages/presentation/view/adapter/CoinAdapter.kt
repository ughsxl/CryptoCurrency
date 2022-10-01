package com.cryptocurrency.packages.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cryptocurrency.packages.databinding.CoinItemBinding
import com.cryptocurrency.packages.domain.model.Coin

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 12:21
 */

class CoinAdapter: RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    private var coins = emptyList<Coin>()
        set(newValue) {
            val diffUtilCallback = CoinsDiffUtilCallback(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)

            field = newValue
            diffUtilResult.dispatchUpdatesTo(this)
            notifyDataSetChanged()
        }

    inner class CoinViewHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root)


    fun setCoinsList(coins: List<Coin>) {
        this@CoinAdapter.coins = coins
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CoinItemBinding.inflate(inflater, parent, false)

        return CoinViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]

        holder.binding.run {
            coinRankTextView.text = "№${coin.rank}"
            coinNameTextView.text = coin.name
            coinSymbolTextView.text = "(${coin.symbol})"
            coinPriceTextView.text = "${coin.price} $"
        }
    }

    override fun getItemCount() = coins.size
}