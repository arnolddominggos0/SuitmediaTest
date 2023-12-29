package com.example.suitmediatest.ui.thirdscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitmediatest.model.User
import com.example.suitmediatest.model.UserResponse
import com.example.suitmediatest.network.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ThirdScreenViewModel : ViewModel() {

    private val data = MutableLiveData<List<User>>()
    private val status = MutableLiveData<UserApi.ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(UserApi.ApiStatus.LOADING)
            try {
                val response = getUserResponse()
                val users = response.data
                data.postValue(users)
                Log.d("ThirdViewModel", "Success: $users")
                status.postValue(UserApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("ThirdViewModel", "Failure: ${e.message}")
                status.postValue(UserApi.ApiStatus.FAILED)
            }
        }
    }

    private suspend fun getUserResponse(): UserResponse {
        return UserApi.service.getUsers()
    }

    fun getData(): LiveData<List<User>> = data

    fun getStatus(): LiveData<UserApi.ApiStatus> = status
}