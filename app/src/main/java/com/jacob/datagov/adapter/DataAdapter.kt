package com.jacob.datagov.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jacob.datagov.BR
import com.jacob.datagov.R
import com.jacob.datagov.database.table.Record
import com.jacob.datagov.viewmodel.ItemDataViewModel
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(private val context: Context, private val items: List<Record>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ViewDataBinding? = DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.item_data, parent, false)
        return UserViewHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ViewDataBinding? = DataBindingUtil.getBinding(holder.itemView)
        var isDecrease = if(position > 0) {
            items[position].volumeOfMobileData!!.toDouble() < items[position-1].volumeOfMobileData!!.toDouble()
        }else{
            false
        }
        binding!!.setVariable(BR.itemData, ItemDataViewModel(items[position],isDecrease))
        binding.executePendingBindings()
        binding.root.btnDrop.setOnClickListener {
            Toast.makeText(context, "drop", Toast.LENGTH_SHORT).show()
        }
    }

    internal class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}