package com.juned.expertclass.suitmediainterntest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import com.juned.expertclass.suitmediainterntest.di.Injection
import com.juned.expertclass.suitmediainterntest.ui.screen1.ScreenOneViewModel
import com.juned.expertclass.suitmediainterntest.ui.screen2.ScreenTwoViewModel
import com.juned.expertclass.suitmediainterntest.ui.screen3.ScreenThreeViewModel

class ViewModelFactory(private val pref: SharedPreference,private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ScreenOneViewModel::class.java) -> {
                ScreenOneViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ScreenTwoViewModel::class.java) -> {
                ScreenTwoViewModel(pref) as T
            }
            modelClass.isAssignableFrom(ScreenThreeViewModel::class.java) -> {
                ScreenThreeViewModel(pref, Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}