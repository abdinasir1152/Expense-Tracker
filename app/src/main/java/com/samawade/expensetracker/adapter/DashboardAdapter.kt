package com.samawade.expensetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.Info
import kotlinx.android.synthetic.main.row_layout.view.*

class DashboardAdapter(): RecyclerView.Adapter<DashboardAdapter.MyViewHolder>() {
    var myList = emptyList<Info>()
//    var customList = emptyList<Info>()


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
        if(myList[position].type == "income"){
                holder.itemView.transactionIconView.setImageResource(R.drawable.ic_income)
            } else {
                holder.itemView.transactionIconView.setImageResource(R.drawable.ic_expense)
            }

        holder.itemView.description.text = myList[position].type
        holder.itemView.amount.text = "$"+myList[position].amount.toString()

    }

    fun setData(newList: List<Info>){
        myList = newList
        notifyDataSetChanged()
    }
//    fun setCustomData(newList: List<Info>, type: String){
//            for( item in myList){
//                while (item.type == type){
//                    customList = myList
//                }
//            }
//        notifyDataSetChanged()
//    }


    override fun getItemCount(): Int {
        return myList.size
    }

}