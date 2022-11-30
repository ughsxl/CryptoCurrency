package com.cryptocurrency.packages.core

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Krupko Illa
 * @since 20.10.2022 is created
 */
interface ManageResources {

	fun string(@StringRes id: Int): String

	fun string(@StringRes id: Int, vararg args: Any): String


	class Base(private val context: Context) : ManageResources {

		override fun string(id: Int): String = context.getString(id)

		override fun string(id: Int, vararg args: Any) = context.getString(id, args)

	}
}