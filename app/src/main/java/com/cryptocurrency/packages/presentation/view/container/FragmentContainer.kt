package com.cryptocurrency.packages.presentation.view.container

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.common.App
import com.cryptocurrency.packages.databinding.FragmentContainerBinding
import com.cryptocurrency.packages.domain.coins_communication.CoinsService
import com.cryptocurrency.packages.presentation.view.screen.ListFragment

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 22:06
 */

class FragmentContainer : AppCompatActivity() {
    private val binding by lazy {
        FragmentContainerBinding.inflate(layoutInflater)
    }

    val coinsService: CoinsService
        get() = (applicationContext as App).coinsService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.black)))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, ListFragment(), ListFragment::class.java.simpleName)
                .commit()
        }

    }


    private fun getCurrentFragment(): Fragment? = try {
        supportFragmentManager.fragments.last()
    } catch (e: NoSuchElementException) {
        null
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val currentFragment = getCurrentFragment()

        if (currentFragment != null && currentFragment.isVisible)
            menuInflater.inflate(R.menu.coin_list_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        coinsService.Communication().sortCoins(item.title.toString())

        return true
    }

}