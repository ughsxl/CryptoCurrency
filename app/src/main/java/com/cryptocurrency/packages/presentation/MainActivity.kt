package com.cryptocurrency.packages.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cryptocurrency.packages.R

/**
 * @author Krupko Illa
 * @since 13.10.2022 is created
 */
class MainActivity : AppCompatActivity(R.layout.fragment_container) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.fragment_container)
	}
}