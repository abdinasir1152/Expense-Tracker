package com.samawade.expensetracker.response

data class TransactionResponse(
    val info: Info,
    val message: String,
    val status: Boolean
)