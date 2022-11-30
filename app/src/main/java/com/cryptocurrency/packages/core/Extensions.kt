package com.cryptocurrency.packages.core

import com.cryptocurrency.packages.R
import com.google.gson.Gson

/**
 * @author Krupko Illa
 * @since 14.11.2022 is created
 */
inline fun <S, reified R> copyProperties(from: S): R {
	val gson = Gson()
	val jsonString = gson.toJson(from)

	return gson.fromJson(jsonString, R::class.java)
}