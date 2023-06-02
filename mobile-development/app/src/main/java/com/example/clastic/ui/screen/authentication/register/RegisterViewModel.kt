package com.example.clastic.ui.screen.authentication.register

import androidx.lifecycle.ViewModel
import com.example.clastic.data.Repository
import com.example.clastic.ui.screen.authentication.components.LoginResult
import com.example.clastic.ui.screen.authentication.components.RegisterResult
import com.example.clastic.ui.screen.authentication.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(private val repository: Repository): ViewModel(){
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _isEnabled = MutableStateFlow(true)
    val isEnabled: StateFlow<Boolean> = _isEnabled.asStateFlow()

    suspend fun registerEmailPass(name: String, email: String, password: String) {
        _isEnabled.value = false
        onRegisterResult(repository.registerEmailPass(name, email, password))
    }

    private fun onRegisterResult(result: RegisterResult) {
        _isEnabled.value = true
        _state.update { it.copy(
            isLoginSuccessful = result.data != null,
            loginError = result.errorMessage
        ) }
    }

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