package com.juned.expertclass.sutimediainterntest.ui.screen3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juned.expertclass.sutimediainterntest.R
import com.juned.expertclass.sutimediainterntest.databinding.ActivityScreenThreeBinding
import com.juned.expertclass.sutimediainterntest.ui.ViewModelFactory
import com.juned.expertclass.sutimediainterntest.ui.adapter.ListUsersAdapter
import com.juned.expertclass.sutimediainterntest.ui.adapter.LoadingStateAdapter


class ScreenThreeActivity : AppCompatActivity() {
    private var _binding: ActivityScreenThreeBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: ScreenThreeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityScreenThreeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()
        val toolbar: Toolbar = binding?.toolbar!!
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory( this@ScreenThreeActivity)
        )[ScreenThreeViewModel::class.java]

        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUsersAdapter { }
        binding?.rvUsers?.adapter = listUserAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                listUserAdapter.retry()
            }
        )
        viewModel.users.observe(this) {
            listUserAdapter.submitData(lifecycle, it)
        }
    }

}