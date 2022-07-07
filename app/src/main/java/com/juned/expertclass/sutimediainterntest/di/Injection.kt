package com.juned.expertclass.sutimediainterntest.di

import android.content.Context
import com.juned.expertclass.sutimediainterntest.data.UserRepository
import com.juned.expertclass.sutimediainterntest.data.local.UserDatabase
import com.juned.expertclass.sutimediainterntest.data.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}