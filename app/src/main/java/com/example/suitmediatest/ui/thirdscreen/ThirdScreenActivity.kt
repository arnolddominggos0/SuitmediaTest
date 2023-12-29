package com.example.suitmediatest.ui.thirdscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import com.example.suitmediatest.network.UserApi
import com.example.suitmediatest.ui.adapter.UserAdapter

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapter: UserAdapter

    private val viewModel: ThirdScreenViewModel by lazy {
        ViewModelProvider(this).get(ThirdScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = UserAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.recyclerView.adapter = adapter

        viewModel.getData().observe(this, Observer { userList ->
            userList?.let {
                adapter.updateData(it)
            }
        })

        viewModel.getStatus().observe(this, Observer { apiStatus ->
            updateProgress(apiStatus)
        })
    }

    private fun updateProgress(status: UserApi.ApiStatus) {
        when (status) {
            UserApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.networkError.visibility = View.GONE
            }
            UserApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.GONE
            }
            UserApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}
