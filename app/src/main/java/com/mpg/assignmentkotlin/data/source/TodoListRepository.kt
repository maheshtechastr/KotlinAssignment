package com.mpg.assignmentkotlin.data.source


import com.mpg.assignmentkotlin.data.model.TodoModel
import com.mpg.assignmentkotlin.data.source.network.ApiInterface
import javax.inject.Inject

class TodoListRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getTodo(): List<TodoModel> {
        return apiInterface.getTodo()
    }

}