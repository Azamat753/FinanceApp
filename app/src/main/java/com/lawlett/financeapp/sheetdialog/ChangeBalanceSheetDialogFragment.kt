package com.lawlett.financeapp.sheetdialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.core.base.BaseBottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.CategoryIconAdapter
import com.lawlett.financeapp.databinding.FragmentChangeBalanceSheetDialogBinding

class ChangeBalanceSheetDialogFragment :
    BaseBottomSheetDialog<FragmentChangeBalanceSheetDialogBinding>(
        FragmentChangeBalanceSheetDialogBinding::inflate
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = CategoryIconAdapter(arrayListOf())
        binding.categoryRecycler.adapter = adapter
    }

    private fun setupUI() {
        with(binding) {
            when (tag) {
                getString(R.string.cost) -> {
                    title.text = tag
                    applyBtn.setBackgroundResource(R.drawable.red_corner_background)
                }
                getString(R.string.income) -> {
                    applyBtn.setBackgroundResource(R.drawable.green_corner_background)
                }
            }
            title.text = tag
        }
    }

    override fun initClickers() {

    }

}