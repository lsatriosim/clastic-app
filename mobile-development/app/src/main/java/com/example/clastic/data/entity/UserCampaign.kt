package com.example.clastic.data.entity

data class UserCampaign(
    val user: User,
    val mission: Mission,
    val status: String,
    val createdAt: String
)
