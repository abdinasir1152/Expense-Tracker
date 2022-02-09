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
import com.samawade.expensetracker.MainViewModel
import com.samawade.expensetracker.MainViewModelFactory
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.ui.DashboardActivity
import com.samawade.expensetracker.util.App
import com.samawade.expensetracker.util.Constants.Companion.ID_TOKEN
import com.samawade.expensetracker.util.Constants.Companion.KEY_PREFERENCES
import com.samawade.expensetracker.util.Constants.Companion.KEY_TOKEN
import com.samawade.expensetracker.util.SharedPref
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var viewModel: MainViewModel

//    private val mSessionManager: SharedPref = SharedPref.getInstance()
//    private val senssion: App = App.preferences
 lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

//        val username = editTextUsername.text.toString()
//        val password = editTextPass.text.toString()



        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPass.text.toString()

            val login = Login(username, password)

            Log.d("Main", username)
            viewModel.getLogin(login)

            viewModel.loginResponse.observe(this, Observer { response ->
                if (response.isSuccessful){
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString(KEY_TOKEN, response.body()?.token)
                    editor.putString(ID_TOKEN, response.body()?.id)
                    editor.apply()

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