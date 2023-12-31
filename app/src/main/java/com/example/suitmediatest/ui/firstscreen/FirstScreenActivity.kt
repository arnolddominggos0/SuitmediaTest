package com.example.suitmediatest.ui.firstscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivityFirstScreenBinding
import com.example.suitmediatest.ui.dialog.MainDialog
import com.example.suitmediatest.ui.secondscreen.SecondScreenActivity
import com.example.suitmediatest.ui.secondscreen.SharePreference

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding
    private lateinit var sharepref: SharePreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharepref = SharePreference(this)

        binding.apply {
            btnCheck.setOnClickListener {
                val inputText = palindromeET.text.toString()

                if (inputText.isNotEmpty()) {
                    val isPalindrome = isPalindrome(inputText)
                    MainDialog(isPalindrome).show(supportFragmentManager, null)
                } else {
                    Toast.makeText(
                        this@FirstScreenActivity,
                        getString(R.string.empty_palindrome), Toast.LENGTH_SHORT
                    ).show()
                }
            }

            btnNext.setOnClickListener {
                val nameET = nameET.text.toString()

                if (nameET.isNotEmpty()) {
                    val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                    sharepref.saveUsername(nameET)
                    intent.putExtra(
                        SharePreference.KEY_USER_NAME,
                        nameET
                    )
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.empty_name), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun isPalindrome(str: String): Boolean {
        var start = 0
        var end = str.length - 1
        while (start < end) {
            while (start < end && !str[start].isLetterOrDigit()) {
                start++
            }
            while (start < end && !str[end].isLetterOrDigit()) {
                end--
            }
            if (str[start] != str[end]) {
                return false
            }
            start++
            end--
        }
        return true
    }
}