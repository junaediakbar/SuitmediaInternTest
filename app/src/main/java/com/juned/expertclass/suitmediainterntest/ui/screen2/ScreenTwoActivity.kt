package com.juned.expertclass.suitmediainterntest.ui.screen2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.juned.expertclass.suitmediainterntest.R
import com.juned.expertclass.suitmediainterntest.data.remote.UserDataResponse
import com.juned.expertclass.suitmediainterntest.databinding.ActivityScreenTwoBinding
import com.juned.expertclass.suitmediainterntest.ui.screen3.ScreenThreeActivity


class ScreenTwoActivity : AppCompatActivity() {

    private var _binding: ActivityScreenTwoBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityScreenTwoBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()
        val toolbar: Toolbar = binding?.toolbar!!
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if(intent.getParcelableExtra<UserDataResponse>(EXTRA_USER)!=null){
            val user =intent.getParcelableExtra<UserDataResponse>(EXTRA_USER)
            user?.let { setupUserData(it) }
        }

        binding?.btnChooseUser?.setOnClickListener {
            startActivity(Intent (this,ScreenThreeActivity::class.java))
        }

    }
    @SuppressLint("SetTextI18n")
    private fun setupUserData(data: UserDataResponse){
        binding?.apply {
            tvUsername.text = data.firstName+" "+data.lastName
            tvselectedUsername.text = data.firstName+" "+data.lastName
        }

    }
    companion object{
        const val EXTRA_USER="extra_name"
    }
}