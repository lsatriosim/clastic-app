package com.example.clastic.ui.screen.authentication.register

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.ui.screen.authentication.components.AuthenticationResult
import com.example.clastic.ui.screen.authentication.login.AuthenticationState
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(private val repository: Repository): ViewModel(){
    private val _state = MutableStateFlow(AuthenticationState())
    val state = _state.asStateFlow()

    private val _isEnabled = MutableStateFlow(true)
    val isEnabled: StateFlow<Boolean> = _isEnabled.asStateFlow()

    suspend fun login(context: Context): IntentSender?{
        return repository.login(context, Identity.getSignInClient(context))
    }

    suspend fun registerEmailPass(name: String, email: String, password: String) {
        _isEnabled.value = false
        onRegisterResult(repository.registerEmailPass(name, email, password))
    }

    suspend fun registerWithIntent(intent: Intent, context: Context) {
        _isEnabled.value = false
        onRegisterResult(repository.loginWithIntent(intent, Identity.getSignInClient(context)))
    }

    private fun onRegisterResult(result: AuthenticationResult?) {
        _isEnabled.value = true
        _state.update { it.copy(
            isAuthSuccessful = result?.data != null,
            authError = result?.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { AuthenticationState() }
    }
}