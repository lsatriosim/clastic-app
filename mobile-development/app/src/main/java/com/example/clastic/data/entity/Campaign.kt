package com.example.clastic.data.entity

data class Campaign(
    val title: String,
    val content: String,
    val reward: Int,
    val photoUrl: String,
    var creator: User
)
