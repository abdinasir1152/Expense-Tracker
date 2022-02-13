package com.samawade.expensetracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.samawade.expensetracker.R
import kotlinx.android.synthetic.main.fragment_transaction_details.*

class TransactionDetailsFragment : Fragment(R.layout.fragment_transaction_details) {
    val args: TransactionDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transaction = args.transaction

        detail_desc.text = transaction.description
        detail_amount.text = transaction.amount.toString()
        detail_type.text = transaction.type
        detail_date.text = transaction.date
        detail_createdAt.text = transaction.createdAt

    }
}