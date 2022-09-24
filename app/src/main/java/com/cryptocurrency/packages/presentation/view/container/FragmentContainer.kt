package com.cryptocurrency.packages.presentation.view.container

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cryptocurrency.packages.R
import com.cryptocurrency.packages.databinding.FragmentContainerBinding
import com.cryptocurrency.packages.presentation.view.screen.ListFragment

/**
 * @author Krupko Illa
 * Created 01.09.2022 at 22:06
 */

class FragmentContainer : AppCompatActivity() {
    private val binding by lazy {
        FragmentContainerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, ListFragment())
                .commit()
        }
    }
}