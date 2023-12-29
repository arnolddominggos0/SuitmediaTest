package com.example.suitmediatest.ui.thirdscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import com.example.suitmediatest.ui.adapter.UserAdapter

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: ThirdScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        adapter = UserAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(ThirdScreenViewModel::class.java)

    }
}
