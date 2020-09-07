package com.mpg.assignmentkotlin.data.remote.network

import com.mpg.assignmentkotlin.data.model.TodoModel
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {

    @GET("/todos")
    suspend fun getTodo(): Response<List<TodoModel>>

}