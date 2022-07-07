package com.juned.expertclass.suitmediainterntest.ui.screen3

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.juned.expertclass.suitmediainterntest.R
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import com.juned.expertclass.suitmediainterntest.databinding.ActivityScreenThreeBinding
import com.juned.expertclass.suitmediainterntest.ui.ViewModelFactory
import com.juned.expertclass.suitmediainterntest.ui.adapter.ListUsersAdapter
import com.juned.expertclass.suitmediainterntest.ui.adapter.LoadingStateAdapter


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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
            finish()
        }

        setupViewModel()
        setRecyclerView()

        binding?.refreshLayout?.setOnRefreshListener {
            setupViewModel()
            setRecyclerView()
            binding?.refreshLayout?.isRefreshing = false
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@ScreenThreeActivity,
            ViewModelFactory(SharedPreference.getInstance(dataStore), this@ScreenThreeActivity)
        )[ScreenThreeViewModel::class.java]
    }

    private fun setRecyclerView() {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)

        val listUserAdapter = ListUsersAdapter { String ->
            viewModel.saveSelectedUser(String)
            finish()
        }
        listUserAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && listUserAdapter.itemCount == 0) {
                if(loadState.append.endOfPaginationReached){
                    binding?.tvNotFound?.text = resources.getString(R.string.data_not_found)
                    binding?.tvNotFound?.isVisible = true
                    binding?.rvUsers?.isVisible = false
                }
                else{
                    binding?.tvNotFound?.text = resources.getString(R.string.cant_get_data)
                    binding?.tvNotFound?.isVisible = true
                    binding?.rvUsers?.isVisible = false
                }


            } else {
                binding?.tvNotFound?.isVisible = false
                binding?.rvUsers?.isVisible = true
            }
        }

        binding?.rvUsers?.adapter = listUserAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                listUserAdapter.retry()
            }
        )

        viewModel.users.observe(this@ScreenThreeActivity) {
            listUserAdapter.submitData(lifecycle, it)
        }

    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}