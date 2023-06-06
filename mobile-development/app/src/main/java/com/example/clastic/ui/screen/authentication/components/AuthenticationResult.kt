package com.example.clastic.ui.screen.authentication.components

import com.example.clastic.data.entity.User

data class AuthenticationResult(
    val data: User?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val userImage: String?,
    val token: String?
)
