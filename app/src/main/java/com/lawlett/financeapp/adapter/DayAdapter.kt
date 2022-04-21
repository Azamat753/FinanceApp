package com.lawlett.financeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.domain.model.PlanModel
import com.lawlett.financeapp.databinding.ItemDayBinding

class DayAdapter : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    private var model: List<PlanModel> = arrayListOf()


    inner class DayViewHolder(val binding: ItemDayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            with(binding) {
                itemTxtDay.text = model[position].date
                itemTxtPlanner.text = model[position].sourceFirst
                itemTxtPlannerNumber.text = model[position].amountFirst
                itemTxtFreeLan.text = model[position].sourceSecond
                itemTxtFreeLanNumber.text = model[position].amountSecond
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = ItemDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addList(planModel: List<PlanModel>) {
        this.model = planModel
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = model.size
}