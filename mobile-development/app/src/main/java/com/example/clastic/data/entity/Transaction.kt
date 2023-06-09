package com.example.clastic.data.entity

data class Transaction(
    val userId: String,
    val ownerId: String,
    val transactionDate: String,
    val totalPoints: Int,
    val transactionList: Map<String, Map<String, Any>>
)