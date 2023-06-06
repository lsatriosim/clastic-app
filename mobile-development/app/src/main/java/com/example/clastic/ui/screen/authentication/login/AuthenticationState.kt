package com.example.clastic.ui.screen.authentication.login

data class AuthenticationState (
    val isAuthSuccessful: Boolean = false,
    val authError: String? = null
)