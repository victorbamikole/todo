package com.victor.interviewtask.data.network.remote

import com.victor.interviewtask.data.model.DetailsItem
import com.victor.interviewtask.data.model.TodosItem
import retrofit2.http.*

interface RetroService {

    @GET("todos")
    suspend fun getAllTodos(): List<TodosItem>

    @GET("todos/{id}")
    suspend fun getTdoDetails(@Path("id") id: Int): DetailsItem

}