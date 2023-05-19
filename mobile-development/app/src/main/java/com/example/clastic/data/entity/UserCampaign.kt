package com.example.clastic.data.entity

data class UserCampaign(
    val user: User,
    val campaign: Campaign,
    val status: String,
    val createdAt: String
)
