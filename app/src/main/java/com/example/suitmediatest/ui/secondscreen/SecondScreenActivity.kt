package com.example.suitmediatest.ui.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding
import com.example.suitmediatest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private var selectedUsername: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        setData()

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setData() {
        binding.apply {
            val username = intent.getStringExtra(USERNAME)
            tvUser.text = username ?: "User"
        }
    }

    override fun onResume() {
        super.onResume()
        selectedUsername = intent.getStringExtra(USERNAME)
        binding.tvUser.text = selectedUsername ?: "User"
    }

    companion object {
        const val USERNAME = "username" }
}
