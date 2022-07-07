package com.juned.expertclass.suitmediainterntest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juned.expertclass.suitmediainterntest.di.Injection
import com.juned.expertclass.suitmediainterntest.ui.screen3.ScreenThreeViewModel

class ViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ScreenThreeViewModel::class.java) -> {
                ScreenThreeViewModel( Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}