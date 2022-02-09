package com.samawade.expensetracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.samawade.expensetracker.R
import com.samawade.expensetracker.util.Contents
import kotlinx.android.synthetic.main.fragment_transaction.*
import transformIntoDatePicker
import java.util.*


class TransactionFragment : Fragment(R.layout.fragment_transaction) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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


    }

}