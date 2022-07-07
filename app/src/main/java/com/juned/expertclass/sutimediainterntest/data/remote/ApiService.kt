package com.juned.expertclass.sutimediainterntest.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUser(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Query("delay") delay: Int
    ): Response<ListUsersResponse>
}