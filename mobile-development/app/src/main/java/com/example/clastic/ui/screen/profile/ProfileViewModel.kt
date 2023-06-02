package com.example.clastic.ui.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(private val repository: Repository): ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    init {
        fetchUser{user, _ ->
            _user.value = user
        }
    }

    private fun fetchUser(callback: (User?, Exception?) -> Unit) {
        repository.getLoggedInUser { user, exception ->
            if (exception != null) {
                Log.d("fetchUser", "Fetching failed ${exception.message.toString()}")
            } else {
                Log.d("fetchUser", "Fetching Success")
            }
            callback(user, exception)
        }
    }
}