package com.samawade.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.Info
import kotlinx.android.synthetic.main.row_layout.view.*

class DashboardAdapter(val isDashboard: Boolean = false): RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {
    var myList = emptyList<Info>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (myList[position].type == "expense" && isDashboard){
            holder.itemView.description.text = myList[position].type+" guure"
            holder.itemView.amount.text = "$"+myList[position].amount.toString()
        } else {
            holder.itemView.description.text = myList[position].type
            holder.itemView.amount.text = "$"+myList[position].amount.toString()
        }
    }

    fun setData(newList: List<Info>){
        myList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myList.size
    }

}