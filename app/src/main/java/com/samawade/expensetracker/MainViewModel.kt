package com.samawade.expensetracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.model.Statement
import com.samawade.expensetracker.model.Statements
import com.samawade.expensetracker.model.Transaction
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.response.LoginResponse
import com.samawade.expensetracker.response.TransactionResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

     val statementResponse: MutableLiveData<Response<Statement>> = MutableLiveData()
    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()
    val transactionResponse: MutableLiveData<Response<TransactionResponse>> = MutableLiveData()
    val allStatementsResponse: MutableLiveData<Response<Statements>> = MutableLiveData()


    fun getStatement(userId: String){
        viewModelScope.launch {
            val response = repository.getStatement(userId)
            statementResponse.value = response
        }
    }

    fun getLogin(login: Login){
        viewModelScope.launch {
            val response = repository.login(login)
            loginResponse.value = response
        }
    }

        fun getTransaction(transaction: Transaction){
        viewModelScope.launch {
            val response = repository.transaction(transaction)
            transactionResponse.value = response
        }
    }


    fun getAllStatements(userId: String){
        viewModelScope.launch {
            val response = repository.getAllStatements(userId)
            allStatementsResponse.value = response
        }
    }


}