package com.mpg.assignmentkotlin.data.remote


import com.mpg.assignmentkotlin.data.model.NetworkResult
import com.mpg.assignmentkotlin.data.model.ResultError
import com.mpg.assignmentkotlin.data.model.ResultSuccess
import com.mpg.assignmentkotlin.data.model.TodoModel
import com.mpg.assignmentkotlin.data.remote.network.ApiInterface
import javax.inject.Inject

class TodoListRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getTodo(): NetworkResult<List<TodoModel>> {
    return try {
        ResultSuccess(apiInterface.getTodo())
    } catch (e: Exception) {
        ResultError(e)
    }
}

}