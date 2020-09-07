package com.mpg.assignmentkotlin.data.model

sealed class NetworkResult<out T : Any> {

    data class Loading<out T : Any>(val data: T?) : NetworkResult<T>()
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Loading<*> -> "Loading[data=$data]"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

