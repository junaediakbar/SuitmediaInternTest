package com.juned.expertclass.suitmediainterntest.ui.screen3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juned.expertclass.suitmediainterntest.data.UserRepository

import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse

class ScreenThreeViewModel(userRepository: UserRepository) : ViewModel() {
    var users: LiveData<PagingData<UserDataResponse>> =
        userRepository.getUser().cachedIn(viewModelScope)

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init{
        _isEmpty.value =false
    }
    fun setIsEmpty(value: Boolean){
        _isEmpty.value =value
    }
}