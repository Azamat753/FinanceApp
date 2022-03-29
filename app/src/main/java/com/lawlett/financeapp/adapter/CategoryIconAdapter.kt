package com.lawlett.financeapp.adapter

import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.financeapp.R
import java.util.ArrayList

class CategoryIconAdapter(data: ArrayList<CategoryIconModel>) :
    BaseQuickAdapter<CategoryIconModel, BaseViewHolder>(R.layout.item_category_icon, data) {
    override fun convert(holder: BaseViewHolder, item: CategoryIconModel) {
        holder.setText(R.id.name, item.name)
        holder.getView<ImageView>(R.id.icon_img).load(item.icon)
    }
}