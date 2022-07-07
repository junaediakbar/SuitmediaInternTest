package com.juned.expertclass.sutimediainterntest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juned.expertclass.sutimediainterntest.data.remote.UserDataResponse

@Database(
    entities = [UserDataResponse::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}