package com.mpg.assignmentkotlin.data.remote


import com.mpg.assignmentkotlin.data.model.NetworkResult
import com.mpg.assignmentkotlin.data.model.TodoModel
import com.mpg.assignmentkotlin.data.remote.network.ApiInterface
import javax.inject.Inject

class TodoListRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getTodoList(): NetworkResult<List<TodoModel>> {

        val result = apiInterface.getTodo()

        return if (result.isSuccessful) {
            if (!result.body().isNullOrEmpty())
                NetworkResult.Success(result.body()!!)
            else NetworkResult.Error(exception = Exception(result.message()))
        } else NetworkResult.Error(exception = Exception(result.message()))
    }

}