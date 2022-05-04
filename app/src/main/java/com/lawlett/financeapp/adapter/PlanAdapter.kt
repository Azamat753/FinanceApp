package com.lawlett.financeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.model.PlanMonthModel
import com.lawlett.financeapp.databinding.ItemPlanBinding

class PlanAdapter(
    private val resultA: ResultA,
) :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {
    private var monthList: List<PlanMonthModel> = arrayListOf()
    private var nowAmountList: List<String> = arrayListOf()
    private var lackAmountList: List<String> = arrayListOf()

    inner class PlanViewHolder(val binding: ItemPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val month = "${monthList[position].month}(${monthList[position].day})"
            with(binding) {
                itemTxtMonth.text = month
                itemTxtExpectedNumber.text = monthList[position].amount
                itemTxtNowNumber.text = nowAmountList[position]
                itemTxtLacksNumber.text = lackAmountList[position]
            }
        }

        fun initAdapter(position: Int) {
            val dayAdapter = DayAdapter()
            dayAdapter.addList(resultA.getDay(monthList[position].id))
            binding.rvDay.adapter = dayAdapter
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemPlanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.initAdapter(position)
        holder.onBind(position)
        holder.itemView.setOnClickListener {
            resultA.translate(
                monthList[position].isMonth,
                monthList[position].id
            )
        }
    }

    fun addModel(
        monthList: List<PlanMonthModel>,
        nowAmountList: List<String>,
        lackAmountList: List<String>,
    ) {
        this.monthList = monthList
        this.nowAmountList = nowAmountList
        this.lackAmountList = lackAmountList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = monthList.size

    interface ResultA {
        fun translate(isMonth: Boolean, id: Int)
        fun getDay(id: Int): List<PlanModel>
    }
}