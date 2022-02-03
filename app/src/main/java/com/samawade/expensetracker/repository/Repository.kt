package com.samawade.expensetracker.repository

import com.samawade.expensetracker.api.RetrofitInstance
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.model.Statement
import com.samawade.expensetracker.response.LoginResponse
import retrofit2.Response

class Repository {

    suspend fun getStatement(userId: String): Response<Statement> {
        return RetrofitInstance.api.getStatement(userId)
    }

    suspend fun login(login: Login): Response<LoginResponse>{
        return RetrofitInstance.api.login(login)
    }

}