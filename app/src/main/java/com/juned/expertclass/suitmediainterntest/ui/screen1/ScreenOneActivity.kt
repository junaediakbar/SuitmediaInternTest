package com.juned.expertclass.suitmediainterntest.ui.screen1

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.juned.expertclass.suitmediainterntest.databinding.ActivityScreenOneBinding
import com.juned.expertclass.suitmediainterntest.ui.screen2.ScreenTwoActivity

class ScreenOneActivity : AppCompatActivity() {

    private var _binding: ActivityScreenOneBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        _binding = ActivityScreenOneBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        binding?.apply {
            btnCheck.setOnClickListener {
                val dialogPalindrome = AlertDialog.Builder(this@ScreenOneActivity)
                if (etPalindrome.text.isEmpty()) {
                    dialogPalindrome.apply {
                        setMessage("Palindrome input is empty")
                        setNegativeButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                    }
                    dialogPalindrome.show()
                } else {
                    if (checkIfPalindrome(etPalindrome.text.toString())) {
                        dialogPalindrome.apply {
                            setMessage("The text is Palindrome")
                            setNegativeButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                        }
                    } else {
                        dialogPalindrome.apply {
                            setMessage("The text isn't Palindrome")
                            setNegativeButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                        }
                    }
                    dialogPalindrome.show()
                }
            }
            btnNext.setOnClickListener {
                goToSecondScreen()
            }
        }

    }

    private fun goToSecondScreen() {
        val etName = binding?.etName
        val intent = Intent(this, ScreenTwoActivity::class.java)
        startActivity(intent)
        etName?.text = null

    }

    private fun checkIfPalindrome(text: String): Boolean {
        val reverseString = text.reversed()
        return text.equals(reverseString, ignoreCase = true)
    }

}