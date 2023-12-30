package com.example.suitmediatest.ui.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding
import com.example.suitmediatest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var sessionManager: SharePreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SharePreference(this)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val storedUsername = sessionManager.getUsername()
        val selectedUser = intent.getStringExtra(FULLNAME)

        val tvUsername = binding.tvUser
        tvUsername.text = storedUsername ?: getString(R.string.user)

        val tvSelectedUser = binding.tvSelectedUsername
        if (selectedUser != null) {
            tvSelectedUser.text = selectedUser
        } else {
            tvSelectedUser.text = getString(R.string.selected_user_name)
        }

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val FULLNAME = "fullname"
    }
}
