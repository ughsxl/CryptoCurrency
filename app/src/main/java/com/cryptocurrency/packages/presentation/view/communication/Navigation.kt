package com.cryptocurrency.packages.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cryptocurrency.packages.R

/**
 * @author Krupko Illa
 * Created 10.10.2022 at 11:04
 */

interface Navigation {

	fun navigate(supportFragmentManager: FragmentManager)

	abstract class Abstract(protected open val screen: Screen) : Navigation {
		override fun navigate(supportFragmentManager: FragmentManager) {
			supportFragmentManager.beginTransaction()
				.executeTransaction()
				.commit()
		}

		protected abstract fun FragmentTransaction.executeTransaction(): FragmentTransaction
	}

	data class Replace(override val screen: Screen) : Abstract(screen) {
		override fun FragmentTransaction.executeTransaction(): FragmentTransaction {
			replace(R.id.fragment_container_view)
		}
	}

}

}