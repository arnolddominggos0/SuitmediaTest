package com.example.suitmediatest.ui.thirdscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.model.User
import com.example.suitmediatest.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThirdScreenViewModel : ViewModel() {
    private val data = MutableLiveData<List<User>>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = UserApi.service.getUser()
                Log.d("ThirdScreenViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("ThirdScreenViewModel", "Failure: ${e.message}")
            }
        }
    }
}