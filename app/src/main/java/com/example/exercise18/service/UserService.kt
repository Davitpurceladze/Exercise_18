package com.example.exercise18.service

import com.example.exercise18.response.UsersListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
//    users?page=1
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UsersListResponse>
}