package com.juned.expertclass.suitmediainterntest.ui.screen1

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.juned.expertclass.suitmediainterntest.data.preference.SharedPreference
import com.juned.expertclass.suitmediainterntest.databinding.ActivityScreenOneBinding
import com.juned.expertclass.suitmediainterntest.ui.ViewModelFactory
import com.juned.expertclass.suitmediainterntest.ui.screen2.ScreenTwoActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ScreenOneActivity : AppCompatActivity() {

    private var _binding: ActivityScreenOneBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel:ScreenOneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityScreenOneBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        setupViewModel()

        val dialogAlert = AlertDialog.Builder(this@ScreenOneActivity)
        binding?.apply {
            btnCheck.setOnClickListener {

                if (etPalindrome.text.isEmpty()) {
                    dialogAlert.apply {
                        setMessage("Palindrome input is empty")
                        setNegativeButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                    }
                    dialogAlert.show()
                } else {
                    if (checkIfPalindrome(etPalindrome.text.toString())) {
                        dialogAlert.apply {
                            setMessage("isPalindrome")
                            setNegativeButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                        }
                    } else {
                        dialogAlert.apply {
                            setMessage("not palindrome")
                            setNegativeButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                        }
                    }
                    dialogAlert.show()
                }
            }
            btnNext.setOnClickListener {
                if(etName.text.isEmpty()){
                    dialogAlert.apply {
                        setMessage("Please input name first")
                        setNegativeButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                    }
                    dialogAlert.show()
                }else{
                    goToSecondScreen(etName.text.toString())
                }

            }
        }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@ScreenOneActivity,
            ViewModelFactory(SharedPreference.getInstance(dataStore),this@ScreenOneActivity)
        )[ScreenOneViewModel::class.java]
    }

    private fun goToSecondScreen(name: String) {
        viewModel.saveUser(name)
        val intent = Intent(this, ScreenTwoActivity::class.java)
        startActivity(intent)
    }

    private fun checkIfPalindrome(text: String): Boolean {
        val reverseString = text.reversed()
        return text.equals(reverseString, ignoreCase = true)
    }

}