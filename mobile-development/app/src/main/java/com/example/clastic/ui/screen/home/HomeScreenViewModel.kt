package com.example.clastic.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: Repository): ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _role = MutableStateFlow("")
    val role : StateFlow<String> get() = _role

    init {
        viewModelScope.launch {
            fetchUser { user, _ ->
                _user.value = user
            }
            _role.value = getRoleByUserId()
        }

    }

    private suspend fun getRoleByUserId(): String {
        return repository.getRoleByUserId()
    }

    private fun fetchUser(callback: (User?, Exception?) -> Unit) {
        repository.getLoggedInUserData { user, exception ->
            if (exception != null) {
                Log.d("fetchUser", "Fetching failed ${exception.message.toString()}")
            } else {
                Log.d("fetchUser", "Fetching Success")
            }
            callback(user, exception)
        }
    }
}