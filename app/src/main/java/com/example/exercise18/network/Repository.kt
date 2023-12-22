package com.example.exercise18.network

import com.example.exercise18.response.User
import com.example.exercise18.response.UsersListResponse
import retrofit2.Response

class Repository {

    suspend fun getUsers(page: Int): Response<UsersListResponse> {
       return Network.getUsersList().getUsers(page)
    }
}