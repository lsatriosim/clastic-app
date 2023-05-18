package com.example.clastic.data.entity

data class Transaction(
    val user: User,
    val dropPoint: DropPoint,
    val transactionDate: String,
    val dropPointOwner: User,
    val plastic: List<Plastic>,
    val totalPrice: Int,
    val status: String
)
