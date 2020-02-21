package com.mpg.assignmentkotlin.data.source.network

import com.mpg.assignmentkotlin.data.model.TodoModel
import retrofit2.http.GET


interface ApiInterface {

    @GET("/todos")
    suspend fun getTodo(): List<TodoModel>

}