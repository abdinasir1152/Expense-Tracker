package com.samawade.expensetracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samawade.expensetracker.MainViewModel
import com.samawade.expensetracker.R

class AuthActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
}