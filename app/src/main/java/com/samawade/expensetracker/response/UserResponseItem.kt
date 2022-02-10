package com.samawade.expensetracker.response

data class UserResponseItem(
    val __v: Int,
    val _id: String,
    val accountNo: Int,
    val createdAt: String,
    val isAdmin: Boolean,
    val name: String,
    val password: String,
    val phone: String,
    val updatedAt: String,
    val username: String
)