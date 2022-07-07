package com.juned.expertclass.sutimediainterntest.ui.screen3

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juned.expertclass.sutimediainterntest.data.UserRepository

import com.juned.expertclass.sutimediainterntest.data.remote.UserDataResponse

class ScreenThreeViewModel(userRepository: UserRepository) : ViewModel() {
    var users: LiveData<PagingData<UserDataResponse>> =
        userRepository.getUser().cachedIn(viewModelScope)
}