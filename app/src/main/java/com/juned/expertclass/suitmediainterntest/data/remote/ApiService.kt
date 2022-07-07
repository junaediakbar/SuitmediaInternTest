package com.juned.expertclass.suitmediainterntest.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUser(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
    ): ListUsersResponse
}