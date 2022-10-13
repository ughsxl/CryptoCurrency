package com.cryptocurrency.packages.presentation.view.screen

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.databinding.CoinListBinding
import com.cryptocurrency.packages.presentation.viewmodel.CoinsViewModel

/**
 * @author Krupko Illa
 * Created 04.09.2022 at 14:52
 */

class ListFragment : BaseFragment<CoinsViewModel>(), MenuProvider {
	private lateinit var binding: CoinListBinding

	override val viewModelClass = CoinsViewModel::class.java
	override val layoutId = R.layout.coin_list


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = CoinListBinding.inflate(layoutInflater, container, false)
		activity?.addMenuProvider(this, viewLifecycleOwner)

		return binding.root
	}

	override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
		menuInflater.inflate(R.menu.coin_list_menu, menu)
	}

	override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
		viewModel.sortCoins(menuItem.title.toString())
		return true
	}

}
