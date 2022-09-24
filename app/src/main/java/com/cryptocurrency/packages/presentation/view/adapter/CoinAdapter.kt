package com.cryptocurrency.packages.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cryptocurrency.packages.databinding.CoinItemBinding
import com.cryptocurrency.packages.domain.model.Coin

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 12:21
 */

class CoinAdapter(private val coins: List<Coin>) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {
    inner class CoinViewHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CoinItemBinding.inflate(inflater, parent, false)

        return CoinViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]

        holder.binding.run {
            coinRankTextView.text = coin.rank
            coinNameTextView.text = coin.name
            coinSymbolTextView.text = coin.symbol
            coinPriceTextView.text = coin.price
        }
    }

    override fun getItemCount() = coins.size
}