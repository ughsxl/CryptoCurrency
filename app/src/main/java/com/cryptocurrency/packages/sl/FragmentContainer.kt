package com.cryptocurrency.packages.presentation.view.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.presentation.view.communication.Navigation
import com.cryptocurrency.packages.presentation.view.screen.Screen
import com.cryptocurrency.packages.sl.ProvideViewModel

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 22:06
 */

class FragmentContainer : AppCompatActivity(), ProvideViewModel {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        if (savedInstanceState == null)
            Navigation.Add(Screen.Coins).navigate(supportFragmentManager)

    }


    override fun <VM : ViewModel> provideViewModel(clazz: Class<VM>, owner: ViewModelStoreOwner): VM {
        (application as ProvideViewModel).provideViewModel(clazz, owner)
    }

}