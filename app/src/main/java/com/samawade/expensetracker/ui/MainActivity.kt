package com.samawade.expensetracker.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.samawade.expensetracker.R
import com.samawade.expensetracker.util.App
import com.samawade.expensetracker.util.Constants.Companion.ID_TOKEN
import com.samawade.expensetracker.util.Constants.Companion.KEY_PREFERENCES
import com.samawade.expensetracker.util.Constants.Companion.KEY_TOKEN

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//
//
        val token = sharedPreferences.getString(KEY_TOKEN, "")
        val id = sharedPreferences.getString(ID_TOKEN, "")

        if (id != null) {
            Log.d("Token", id)
        }


        if (token.isNullOrBlank()){
            startActivity(Intent(this, AuthActivity::class.java))
        } else {
            startActivity(Intent(this, DashboardActivity::class.java))
//            editor.clear()
//            editor.apply()
            Log.d("Token", token)
        }
    }
}