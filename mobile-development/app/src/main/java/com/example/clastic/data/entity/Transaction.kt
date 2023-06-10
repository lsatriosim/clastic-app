package com.example.clastic.data.entity

data class Transaction(
    val id: String,
    val location: String,
    val userId: String,
    val ownerId: String,
    val transactionDate: String,
    val totalPoints: Int,
    val transactionList: Map<String, Map<String, Any>>
)