package com.cryptocurrency.packages.presentation.view.communication

import kotlinx.coroutines.Job

/**
 * @author Krupko Illa
 * Created 10.10.2022 at 21:54
 */

interface ListCommunication {

	fun loadCoins(): Job

	fun sortCoins(by: String): Job

}