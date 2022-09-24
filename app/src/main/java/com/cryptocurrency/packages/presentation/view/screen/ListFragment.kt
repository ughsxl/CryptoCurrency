package com.cryptocurrency.packages.presentation.view.screen

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.common.UiText
import com.cryptocurrency.packages.data.model.dto.Resource
import com.cryptocurrency.packages.databinding.CoinListBinding
import com.cryptocurrency.packages.presentation.view.adapter.CoinAdapter
import com.cryptocurrency.packages.presentation.viewmodel.factory.factory
import com.cryptocurrency.packages.presentation.viewmodel.vm.ListViewModel
import kotlinx.coroutines.delay

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 14:52
 */

class ListFragment : Fragment(R.layout.coin_list) {
    private lateinit var binding: CoinListBinding
    private val viewModel: ListViewModel by viewModels { factory() }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CoinListBinding.inflate(layoutInflater, container, false)

        launchCoins()

        return binding.root
    }

    private fun launchCoins() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launchWhenStarted {
            viewModel.coins.collect { coins ->
                when (coins.state) {
                    is Resource.Success -> {
                        binding.run {
                            downloadingResultImage.setImageResource(R.drawable.done_icon)
                            downloadingState.text = coins.message
                            progressBar.isVisible = false
                            frame.isVisible = true
                        }
                        delay(1500)
                        binding.run {
                            recyclerView.isVisible = true
                            recyclerView.adapter = CoinAdapter(coins.data)
                            downloadingState.isVisible = false
                            downloadingResultImage.isVisible = false
                            frame.isVisible = false
                        }
                        coins.state = Resource.Empty()
                    }

                    is Resource.Error -> {
                        binding.run {
                            downloadingState.isVisible = true
                            downloadingState.text = coins.message
                            downloadingResultImage.setImageResource(R.drawable.error_icon)
                            progressBar.isVisible = false
                            frame.isVisible = true
                        }
                    }

                    is Resource.Loading -> {
                        binding.run {
                            progressBar.isVisible = true
                            downloadingState.isVisible = true
                            downloadingState.text = coins.message
                            downloadingResultImage.setImageResource(R.drawable.downloading_icon)
                            frame.isVisible = false
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}