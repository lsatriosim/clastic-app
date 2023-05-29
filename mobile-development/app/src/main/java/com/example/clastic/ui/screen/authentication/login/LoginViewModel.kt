package com.example.clastic.ui.screen.authentication.login

import androidx.lifecycle.ViewModel
import com.example.clastic.ui.screen.authentication.components.LoginResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onLoginResult(result: LoginResult) {
        _state.update { it.copy(
            isLoginSuccessful = result.data != null,
            loginError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { LoginState() }
    }
}