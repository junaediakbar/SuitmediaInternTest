package com.juned.expertclass.suitmediainterntest.ui.screen2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import com.juned.expertclass.suitmediainterntest.domain.model.User

class ScreenTwoViewModel(private val pref: SharedPreference) : ViewModel() {
    fun getData(): LiveData<User> {
        return pref.getData().asLiveData()
    }
}