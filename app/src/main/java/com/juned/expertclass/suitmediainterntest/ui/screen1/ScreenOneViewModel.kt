package com.juned.expertclass.suitmediainterntest.ui.screen1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import kotlinx.coroutines.launch

class ScreenOneViewModel(private val pref: SharedPreference) : ViewModel() {
    fun saveUser(user: String) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}
