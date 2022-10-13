package com.cryptocurrency.packages.common

import android.app.Application
import com.cryptocurrency.packages.presentation.view.adapter.CoinsService

/**
 * @author Krupko Illa
 * Created 28.09.2022 at 22:08
 */

class App : Application() {

	val coinsService: CoinsService = CoinsService()

}