package com.juned.expertclass.suitmediainterntest.data

import androidx.lifecycle.LiveData
import androidx.paging.*

import com.juned.expertclass.suitmediainterntest.data.local.UserDatabase
import com.juned.expertclass.suitmediainterntest.data.remote.ApiService
import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse

class UserRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {
    @OptIn(ExperimentalPagingApi::class)
    fun getUser(): LiveData<PagingData<UserDataResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.userDao().getAllUser()
            }
        ).liveData
    }
}