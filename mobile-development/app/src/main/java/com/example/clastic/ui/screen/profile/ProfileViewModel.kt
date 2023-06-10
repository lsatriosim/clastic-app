package com.example.clastic.ui.screen.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clastic.data.Repository
import com.example.clastic.data.entity.User
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository): ViewModel() {
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _transactionCount = MutableStateFlow(0)
    val transactionCount = _transactionCount.asStateFlow()

    private val _weightTotal = MutableStateFlow(0f)
    val weightTotal = _weightTotal.asStateFlow()

    init {
        viewModelScope.launch {
            fetchUser { user, _ ->
                _user.value = user
            }
        }
    }

    suspend fun getDataSummary() {
        _weightTotal.value = repository.getSumOfWeightByUid()
        _transactionCount.value = repository.getTransactionCountByUserId()
    }


    suspend fun logout(context: Context) {
        repository.logout(Identity.getSignInClient(context))
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