package com.cryptocurrency.packages.data.remote.model.dto

/**
 * @author Krupko Illa
 * Created 23.09.2022 at 17:17
 */

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Empty<T>(data: T? = null) : Resource<T>(data)
}
