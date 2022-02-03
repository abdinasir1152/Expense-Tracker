package com.samawade.expensetracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.model.Statement
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.response.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

     val statementResponse: MutableLiveData<Response<Statement>> = MutableLiveData()
    val loginResponse: MutableLiveData<Response<LoginResponse>> = MutableLiveData()

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


}