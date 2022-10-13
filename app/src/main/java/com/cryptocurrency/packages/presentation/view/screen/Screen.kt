package com.cryptocurrency.packages.presentation.view.communication

import androidx.fragment.app.Fragment

/**
 * @author Krupko Illa
 * Created 10.10.2022 at 11:07
 */

sealed class Screen {

	abstract fun fragment(): Class<out BaseFragment<*>>

	object Coins : Screen() {
		override fun fragment(): Class<Fragment> {
			TODO("Not yet implemented")
		}
	}
}