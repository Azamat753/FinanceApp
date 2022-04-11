package com.lawlett.financeapp.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.databinding.ItemCategoryIconBinding

class CategoryAdapter(private val list: List<CategoryIconModel>, private val result: Result) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val handler = Handler(Looper.getMainLooper())

    inner class CategoryViewHolder(val binding: ItemCategoryIconBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(name: String) {
            binding.name.text = name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding: ItemCategoryIconBinding =
            ItemCategoryIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            when (payloads[0]) {
                1 -> {
                    for (item in 0..itemCount) {
                        if (item == position) {
                            holder.binding.bgCard.setBackgroundResource(R.drawable.bg_black_light)
                        } else {
                            handler.post {
                                notifyItemChanged(item, 2)
                            }
                        }
                    }
                }
                2 -> {
                    holder.binding.bgCard.setBackgroundResource(R.drawable.bg_black)
                }
            }
        }

        super.onBindViewHolder(holder, position, payloads)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(list[position].name)
        holder.itemView.setOnClickListener {
            notifyItemChanged(position, 1)
            result.click(list[position].name, list[position].icon)
        }
    }

    override fun getItemCount(): Int = list.size
    interface Result {
        fun click(name: String, icon: Int)
    }
}