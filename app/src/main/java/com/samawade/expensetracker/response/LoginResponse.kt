package com.samawade.expensetracker.response

data class LoginResponse(
    val id: String,
    val message: String,
    val status: Boolean,
    val token: String
)