package com.mpg.assignmentkotlin.data.model

sealed class NetworkResult<out T : Any>

class ResultSuccess<out T : Any>(val data: T) : NetworkResult<T>()
class ResultError(
    val exception: Throwable,
    val message: String = exception.localizedMessage
) : NetworkResult<Nothing>()