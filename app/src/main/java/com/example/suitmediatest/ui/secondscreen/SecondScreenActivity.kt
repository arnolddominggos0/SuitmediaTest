package com.example.suitmediatest.ui.secondscreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding
import com.example.suitmediatest.model.User
import com.example.suitmediatest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    companion object {
        const val USERNAME = "username"
        const val FULLNAME = "fullname"
        const val PREF_USERNAME = "pref_username"
    }

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)

        var storedUsername =
            sharedPreferences.getString(PREF_USERNAME, getString(R.string.user))

        val usernameFromIntent = intent.getStringExtra(USERNAME)
        if (!usernameFromIntent.isNullOrEmpty()) {
            storedUsername = usernameFromIntent
            sharedPreferences.edit().putString(PREF_USERNAME, storedUsername).apply()
        }

        binding.tvUser.text = storedUsername

        setData()

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setData() {
        binding.apply {
            val selectedUsername = intent.getStringExtra(USERNAME)

            // Update tvSelectedUsername based on selectedUsername if available
            if (selectedUsername != null) {
                val user = intent.getParcelableExtra<User>(FULLNAME)
                if (user != null) {
                    val firstName = user.first_name
                    val lastName = user.last_name
                    val name = "$firstName $lastName"
                    tvSelectedUsername.text = name
                } else {
                    tvSelectedUsername.text = getString(R.string.selected_user_name)
                }
            }
        }
    }
}

