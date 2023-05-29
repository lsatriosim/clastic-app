package com.example.clastic.ui.screen.authentication.components

data class LoginResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val userImage: String?,
    val token: String?
)
