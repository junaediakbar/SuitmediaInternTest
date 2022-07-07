package com.juned.expertclass.suitmediainterntest.ui.screen2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.juned.expertclass.suitmediainterntest.R
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import com.juned.expertclass.suitmediainterntest.databinding.ActivityScreenTwoBinding
import com.juned.expertclass.suitmediainterntest.ui.ViewModelFactory
import com.juned.expertclass.suitmediainterntest.ui.screen3.ScreenThreeActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ScreenTwoActivity : AppCompatActivity() {

    private var _binding: ActivityScreenTwoBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: ScreenTwoViewModel

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

        binding?.btnChooseUser?.setOnClickListener {
            startActivity(Intent(this, ScreenThreeActivity::class.java))
        }

        setupViewModel()

        viewModel.getData().observe(this) {
            binding?.tvUsername?.text = it.user
            binding?.tvselectedUsername?.text = it.selectedUser
        }


    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@ScreenTwoActivity,
            ViewModelFactory(SharedPreference.getInstance(dataStore), this@ScreenTwoActivity)
        )[ScreenTwoViewModel::class.java]
    }

}