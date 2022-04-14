package com.lawlett.financeapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.databinding.ItemHistoryCategoryBinding

class HistoryCategoryAdapter :
    RecyclerView.Adapter<HistoryCategoryAdapter.HistoryCategoryViewHolder>() {
    private var list: List<BalanceModel> = arrayListOf()

    inner class HistoryCategoryViewHolder(val binding: ItemHistoryCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val temp = "- ${list[position].cost}"
            binding.category.text = list[position].iconName
            binding.amount.text = temp
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCategoryViewHolder {
        val binding = ItemHistoryCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HistoryCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryCategoryViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<BalanceModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}