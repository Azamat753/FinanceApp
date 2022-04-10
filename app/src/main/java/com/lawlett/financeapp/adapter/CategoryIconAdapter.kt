package com.lawlett.financeapp.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.financeapp.R

class CategoryIconAdapter(data: List<CategoryIconModel>) :
    BaseQuickAdapter<CategoryIconModel, BaseViewHolder>(R.layout.item_category_icon) {
    override fun convert(holder: BaseViewHolder, item: CategoryIconModel) {
        holder.setText(R.id.name, item.name)
        Glide.with(holder.getView<ImageView>(R.id.icon_img)).load(item.icon)
            .into(holder.getView(R.id.icon_img))
    }
}