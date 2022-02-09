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
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.samawade.expensetracker.MainViewModel
import com.samawade.expensetracker.MainViewModelFactory
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.Login
import com.samawade.expensetracker.model.Transaction
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.ui.DashboardActivity
import com.samawade.expensetracker.util.Constants
import com.samawade.expensetracker.util.Contents
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_transaction.*
import transformIntoDatePicker
import java.util.*


class TransactionFragment : Fragment(R.layout.fragment_transaction) {
    private lateinit var viewModel: MainViewModel

    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(Constants.KEY_PREFERENCES, Context.MODE_PRIVATE)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        val transactionTypeAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.item_autocomplete_layout,
                Contents.transactionType
            )

        et_transactionType.setAdapter(transactionTypeAdapter)

        et_when.transformIntoDatePicker(
            requireContext(),
            "dd/MM/yyyy",
            Date()
        )

        val desc = et_desc.text.toString()
        val amount = et_amount.text.toString()
        val type = et_transactionType.text.toString()
        val date = et_when.text.toString()



        btn_save_transaction.setOnClickListener {
            val desc = et_desc.text.toString()
            val amount = et_amount.text.toString().toDouble()
            val type = et_transactionType.text.toString()
            val date = et_when.text.toString()

            val transaction = Transaction(amount.toString(),desc, type, "61dc2607057b9300094a3217", date)

            viewModel.getTransaction(transaction)

            viewModel.transactionResponse.observe(this, androidx.lifecycle.Observer { response->

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