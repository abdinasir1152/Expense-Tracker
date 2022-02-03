package com.samawade.expensetracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samawade.expensetracker.MainViewModel
import com.samawade.expensetracker.MainViewModelFactory
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.ui.DashboardActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val username = editTextUsername.text.toString()
        val password = editTextPass.text.toString()


        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPass.text.toString()

            val login = Login(username, password)

            Log.d("Main", username)
            viewModel.getLogin(login)

            viewModel.loginResponse.observe(this, Observer { response ->
                if (response.isSuccessful){
                    Log.d("Main", response.body().toString())
                    Log.d("Main", response.code().toString())
                    Log.d("Main", response.message())
                    startActivity(Intent(activity, DashboardActivity::class.java))
                } else {
                    Toast.makeText(activity, response.code(), Toast.LENGTH_LONG).show()
                }

            })
        }





    }

}