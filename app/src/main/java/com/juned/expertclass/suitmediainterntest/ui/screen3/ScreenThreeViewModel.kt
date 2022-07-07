package com.juned.expertclass.suitmediainterntest.ui.screen3

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juned.expertclass.suitmediainterntest.data.UserRepository
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse
import kotlinx.coroutines.launch

class ScreenThreeViewModel(private val pref: SharedPreference,userRepository: UserRepository) : ViewModel() {
    var users: LiveData<PagingData<UserDataResponse>> =
        userRepository.getUser().cachedIn(viewModelScope)

    fun saveSelectedUser(selectedUser: String) {
        viewModelScope.launch {
            pref.saveSelectedUser(selectedUser)
        }
    }
}