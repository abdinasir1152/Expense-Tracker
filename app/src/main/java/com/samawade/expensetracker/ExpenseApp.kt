package com.samawade.expensetracker

import android.app.Application
import android.content.Context

class ExpenseApp: Application() {

    companion object{
        private var instance: ExpenseApp? = null

        fun context(): Context? {
            return instance?.getApplicationContext()
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()

    }

}