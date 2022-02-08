package com.samawade.expensetracker.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samawade.expensetracker.MainViewModel
import com.samawade.expensetracker.MainViewModelFactory
import com.samawade.expensetracker.R
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.ui.DashboardActivity
import com.samawade.expensetracker.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_expense.*
import kotlinx.android.synthetic.main.fragment_income.*

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    lateinit var viewModel: MainViewModel
//    private val viewModel: MainViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

//        viewModel =(activity as DashboardActivity).viewModel
        Log.d("Response", "TEST")


        viewModel.getStatement("61dc2607057b9300094a3217")

        viewModel.statementResponse.observe(this, Observer { response ->
            Log.d("Response", "TEST")


            if(response.isSuccessful){
                    textIncome.text = "$"+response.body()?.userincome.toString()
                    textExpense.text = response.body()?.userExpense.toString()
                    textBalance.text = response.body()?.balance.toString()

//                textTotalIncome.text = response.body()?.userincome.toString()
//                textTotalExpense.text = response.body()?.userExpense.toString()
//
                Log.d("Response", response.body()?.userincome.toString())
                Log.d("Response", response.body()?.userExpense.toString())
                Log.d("Response", response.body()?.balance.toString())
//                textView.text = response.body()?.get(0)?.username!!
            } else{
                Log.d("Response", response.errorBody().toString())
            }
        })
    }





}