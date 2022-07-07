package com.juned.expertclass.sutimediainterntest.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

import com.juned.expertclass.sutimediainterntest.data.local.UserDatabase
import com.juned.expertclass.sutimediainterntest.data.remote.ApiService
import com.juned.expertclass.sutimediainterntest.data.remote.UserDataResponse

class UserRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {
    fun getUser(): LiveData<PagingData<UserDataResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 3
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}