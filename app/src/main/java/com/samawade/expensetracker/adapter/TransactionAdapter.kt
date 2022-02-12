package com.samawade.expensetracker.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samawade.expensetracker.R
import com.samawade.expensetracker.model.Info
import kotlinx.android.synthetic.main.row_layout.view.*

class TransactionAdapter(): RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {
//    var myList = emptyList<Info>()
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    private val differCallback =object : DiffUtil.ItemCallback<Info>(){
        override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_layout,
                parent,
                false
            )
        )
    }

    private var onItemClickListener: ((Info) -> Unit)? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.itemView.apply {
            if(item.type == "income"){
                transactionIconView.setImageResource(R.drawable.ic_income)
            } else {
                transactionIconView.setImageResource(R.drawable.ic_expense)
            }
            description.text = item.description
            holder.itemView.amount.text = "$"+item.amount.toString()
            setOnClickListener {
            onItemClickListener?.let { it(item) }
                Log.d("Response", "item clicked")
            }
        }

//        if(myList[position].type == "income"){
//                holder.itemView.transactionIconView.setImageResource(R.drawable.ic_income)
//            } else {
//                holder.itemView.transactionIconView.setImageResource(R.drawable.ic_expense)
//            }
//
//        holder.itemView.description.text = myList[position].type
//        holder.itemView.amount.text = "$"+myList[position].amount.toString()
//
//        holder.itemView.setOnClickListener {
////            onItemClickListener?.let { it(item) }
//            Log.d("Response", "item clicked")
//        }

    }
//
//    fun setData(newList: List<Info>){
//        myList = newList
//        notifyDataSetChanged()
//    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (Info) -> Unit) {
        onItemClickListener = listener
    }

}