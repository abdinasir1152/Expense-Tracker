package com.samawade.expensetracker.api

import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.model.Statement
import com.samawade.expensetracker.model.Statements
import com.samawade.expensetracker.model.Transaction
import com.samawade.expensetracker.response.LoginResponse
import com.samawade.expensetracker.response.TransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RemoteApiServices {
        @GET("statements/info/{userID}")
    suspend fun getStatement(
        @Path("userID") userId: String
    ): Response<Statement>

    @POST("auth")
    suspend fun login(
        @Body login: Login
    ): Response<LoginResponse>

    @POST("transection")
    suspend fun transaction(
        @Body transaction: Transaction
    ): Response<TransactionResponse>

    @GET("statements/{userID}")
    suspend fun getAllStatements(
        @Path("userID") userId: String
    ): Response<Statements>


}