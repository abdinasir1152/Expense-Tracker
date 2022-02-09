package com.samawade.expensetracker.model

data class Transaction(
    val amount: String,
    val description: String,
    val type: String,
    val userID: String,
    val date: String
)