package com.juned.expertclass.sutimediainterntest.ui.screen2

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import com.juned.expertclass.sutimediainterntest.R

import com.juned.expertclass.sutimediainterntest.databinding.ActivityScreenTwoBinding
import com.juned.expertclass.sutimediainterntest.ui.screen3.ScreenThreeActivity


class ScreenTwoActivity : AppCompatActivity() {

    private var _binding: ActivityScreenTwoBinding? = null
    private val binding get() = _binding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
        val intent = intent
        val nama = intent.getStringExtra("nama")

        binding?.tvUsername?.text = nama.toString()

        binding?.btnChooseUser?.setOnClickListener {
            startActivity(Intent (this,ScreenThreeActivity::class.java))
        }

    }
}