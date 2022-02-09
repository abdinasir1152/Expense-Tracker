package com.samawade.expensetracker.repository

import com.samawade.expensetracker.api.RetrofitInstance
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.model.Statement
import com.samawade.expensetracker.model.Statements
import com.samawade.expensetracker.model.Transaction
import com.samawade.expensetracker.response.LoginResponse
import com.samawade.expensetracker.response.TransactionResponse
import retrofit2.Response

class Repository {

    suspend fun getStatement(userId: String): Response<Statement> {
        return RetrofitInstance.api.getStatement(userId)
    }

    suspend fun login(login: Login): Response<LoginResponse>{
        return RetrofitInstance.api.login(login)
    }

    suspend fun transaction(transaction: Transaction): Response<TransactionResponse>{
        return RetrofitInstance.api.transaction(transaction)
    }

    suspend fun getAllStatements(userId: String): Response<Statements> {
        return RetrofitInstance.api.getAllStatements(userId)
    }

}