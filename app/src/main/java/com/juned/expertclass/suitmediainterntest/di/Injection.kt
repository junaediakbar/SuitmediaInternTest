package com.juned.expertclass.suitmediainterntest.di

import android.content.Context
import com.juned.expertclass.suitmediainterntest.data.UserRepository
import com.juned.expertclass.suitmediainterntest.data.local.UserDatabase
import com.juned.expertclass.suitmediainterntest.data.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}