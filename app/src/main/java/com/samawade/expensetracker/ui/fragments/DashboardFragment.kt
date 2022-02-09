package com.samawade.expensetracker.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.samawade.expensetracker.MainViewModel
import com.samawade.expensetracker.MainViewModelFactory
import com.samawade.expensetracker.R
import com.samawade.expensetracker.adapter.DashboardAdapter
import com.samawade.expensetracker.repository.Repository
import com.samawade.expensetracker.ui.DashboardActivity
import com.samawade.expensetracker.ui.MainActivity
import com.samawade.expensetracker.util.Constants
import com.samawade.expensetracker.util.Constants.Companion.KEY_PREFERENCES
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_expense.*
import kotlinx.android.synthetic.main.fragment_income.*

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    lateinit var sharedPreferences: SharedPreferences

    lateinit var viewModel: MainViewModel
//    private val viewModel: MainViewModel by activityViewModels()
    private val myAdapter by lazy { DashboardAdapter(true) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        sharedPreferences = requireContext().getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val token = sharedPreferences.getString(Constants.KEY_TOKEN, "")
        val id = sharedPreferences.getString(Constants.ID_TOKEN, "")

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        id?.let { viewModel.getStatement(it) }
        id?.let { viewModel.getAllStatements(it) }


        viewModel.statementResponse.observe(this, Observer { response ->
            Log.d("Response", "TEST")


            if(response.isSuccessful){
                    textIncome.text = "$"+response.body()?.userincome.toString()
                    textExpense.text = "$"+response.body()?.userExpense.toString()
                    textBalance.text = "$"+response.body()?.balance.toString()

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

        viewModel.allStatementsResponse.observe(this, Observer { response ->
            Log.d("Response", "TEST")
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it.info) }
//                textView.text = response.body()?.get(0)?.username!!
            } else{
                Log.d("Response", response.errorBody().toString())
            }
        })


    }



    fun setUpRecyclerView(){
        recyclerView_dashboard.adapter = myAdapter
        recyclerView_dashboard.layoutManager = LinearLayoutManager(activity)
    }

}