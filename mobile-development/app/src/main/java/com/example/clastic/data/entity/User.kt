package com.example.clastic.data.entity

data class User(
    val username: String,
    val password: String,
    val coin: Int,
    val userPhoto: String,
    val level: Int,
    val exp: Int,
    val createdAt: String,
    val role: String
)
