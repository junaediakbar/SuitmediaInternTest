package com.juned.expertclass.suitmediainterntest.data.remote

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class ListUsersResponse(
    @field:SerializedName("per_page")
    val perPage: Int,

    @field:SerializedName("data")
    val data: List<UserDataResponse>,

    @field:SerializedName("page")
    val page: Int
)

@Parcelize
@Entity(tableName = "user")
data class UserDataResponse(
    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("avatar")
    val avatar: String,

    @field:SerializedName("first_name")
    val firstName: String,

    @PrimaryKey
    @field:SerializedName("email")
    val email: String
): Parcelable