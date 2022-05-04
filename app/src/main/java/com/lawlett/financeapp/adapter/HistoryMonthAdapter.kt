package com.lawlett.financeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.databinding.ItemHistoryMonthBinding

class HistoryMonthAdapter : RecyclerView.Adapter<HistoryMonthAdapter.HistoryMonthViewHolder>() {
    private var list: List<BalanceModel> = arrayListOf()

    inner class HistoryMonthViewHolder(val binding: ItemHistoryMonthBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.txtMonth.text = list[position].month
            binding.amountBalance.text = list[position].balance
            binding.amountCost.text = list[position].cost
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryMonthViewHolder {
        val binding = ItemHistoryMonthBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return HistoryMonthViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryMonthViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size

    fun addList(list: List<BalanceModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}