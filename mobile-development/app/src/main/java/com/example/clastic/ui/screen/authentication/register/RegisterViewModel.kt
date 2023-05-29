package com.example.clastic.ui.screen.authentication.register

import androidx.lifecycle.ViewModel
import com.example.clastic.ui.screen.authentication.components.LoginResult
import com.example.clastic.ui.screen.authentication.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel(){
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onRegisterResult(result: LoginResult) {
        _state.update { it.copy(
            isLoginSuccessful = result.data != null,
            loginError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { LoginState() }
    }
}