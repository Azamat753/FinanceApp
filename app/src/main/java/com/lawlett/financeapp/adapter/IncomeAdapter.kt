package com.lawlett.financeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.databinding.ItemCostBinding
import com.lawlett.financeapp.databinding.ItemIncomeBinding

class IncomeAdapter : RecyclerView.Adapter<IncomeAdapter.CostViewHolder>() {
    private var list: List<BalanceModel> = ArrayList()

    inner class CostViewHolder(private val binding: ItemIncomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(income: String?, date: String?, icon: Int?, iconName: String?) {
            binding.name.text = iconName
            binding.amount.text = income
            binding.date.text = date
            binding.iconImg.load(icon!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostViewHolder {
        val binding: ItemIncomeBinding = ItemIncomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CostViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        holder.onBind(
            list[position].income,
            list[position].date,
            list[position].icon,
            list[position].iconName
        )
    }

    override fun getItemCount(): Int = list.size
    fun addModel(balanceModel: List<BalanceModel>) {
        list = balanceModel
        notifyDataSetChanged()
    }
}