package com.juned.expertclass.suitmediainterntest.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserDataResponse>)

    @Query("SELECT * FROM user")
    fun getAllUser(): PagingSource<Int, UserDataResponse>

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}