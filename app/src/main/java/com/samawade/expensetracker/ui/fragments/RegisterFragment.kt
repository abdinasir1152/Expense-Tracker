package com.samawade.expensetracker.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.User
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.ui.DashboardActivity
import com.samawade.expensetracker.util.Constants
import com.samawade.expensetracker.viewModel.MainViewModel
import com.samawade.expensetracker.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_transaction.*

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var viewModel: MainViewModel

    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(Constants.KEY_PREFERENCES, Context.MODE_PRIVATE)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        btn_signup.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()
            val username = editTextTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            val register = User(name,password,phone,username)

            viewModel.getUser(register)

            viewModel.userResponse.observe(this, Observer { response->
                Log.d("Main", response.code().toString())
                if (response.isSuccessful){
                    Log.d("Main", response.body().toString())
                    Log.d("Main", response.code().toString())
                    Log.d("Main", response.message())

                    startActivity(Intent(activity, DashboardActivity::class.java))
                } else {
                    Toast.makeText(activity, response.code(), Toast.LENGTH_LONG).show()
                    Log.d("Main", response.code().toString())
                }
            })
        }

    }


}