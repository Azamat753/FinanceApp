package com.lawlett.financeapp.sheetdialog

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.core.base.BaseBottomSheetDialog
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.CategoryAdapter
import com.lawlett.financeapp.adapter.CategoryIconAdapter
import com.lawlett.financeapp.databinding.FragmentBalanceBinding
import com.lawlett.financeapp.databinding.FragmentChangeBalanceSheetDialogBinding
import com.lawlett.financeapp.presenter.ChangeBalancePresenter
import com.lawlett.financeapp.utils.checkNumber
import com.lawlett.financeapp.utils.toast
import com.lawlett.view.ChangeBalanceView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChangeBalanceSheetDialogFragment(private val result: Result) :
    BaseBottomSheetDialog<FragmentChangeBalanceSheetDialogBinding>(
        FragmentChangeBalanceSheetDialogBinding::inflate
    ), ChangeBalanceView, CategoryAdapter.Result {


    @Inject
    lateinit var hiltPresenter: ChangeBalancePresenter
    private lateinit var adapter: CategoryAdapter

    private var icon = 0
    var iconName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        initAdapter()
    }

    private fun initAdapter() {
        binding.categoryRecycler.adapter = adapter
    }

    private fun setupUI() {
        with(binding) {
            when (tag) {
                getString(R.string.cost) -> {
                    title.text = tag
                    applyBtn.setBackgroundResource(R.drawable.red_corner_background)
                    adapter = CategoryAdapter(
                        hiltPresenter.getCostIcon(),
                        this@ChangeBalanceSheetDialogFragment
                    )
                }
                getString(R.string.income) -> {
                    applyBtn.setBackgroundResource(R.drawable.green_corner_background)
                    adapter = CategoryAdapter(
                        hiltPresenter.getCategoryIcon(),
                        this@ChangeBalanceSheetDialogFragment
                    )
                }
            }
            title.text = tag
        }
    }

    override fun initClickers() {
        var amount = ""
        with(binding) {
            when (tag) {
                getString(R.string.cost) -> {
                    applyBtn.setOnClickListener {
                        amount = amountEd.text.toString()
                        if (amount.isNotBlank()) {
                            if (checkNumber(icon)) {
                                result.createCost(amount, icon, iconName)
                                dismiss()
                            } else
                                toast(requireContext(), getString(R.string.choose_category))
                        } else
                            toast(requireContext(), getString(R.string.enter_amount))
                    }
                }
                getString(R.string.income) -> {
                    applyBtn.setOnClickListener {
                        amount = amountEd.text.toString()
                        if (amount.isNotBlank()) {
                            if (checkNumber(icon)) {
                                result.createIncome(amount, icon, iconName)
                                dismiss()
                            } else
                                toast(requireContext(), getString(R.string.choose_category))
                        } else
                            toast(requireContext(), getString(R.string.enter_amount))
                    }
                }
            }
        }
    }


    override fun closeDialog() {
        dismiss()
    }

    override fun click(name: String, icon: Int) {
        this.icon = icon
        iconName = name
    }

    interface Result {
        fun createIncome(amount: String, icon: Int, iconName: String)
        fun createCost(amount: String, icon: Int, iconName: String)
    }
}