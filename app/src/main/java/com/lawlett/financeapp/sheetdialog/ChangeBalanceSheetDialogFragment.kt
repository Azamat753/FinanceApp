package com.lawlett.financeapp.sheetdialog

import android.os.Bundle
import android.view.View
import com.example.core.base.BaseBottomSheetDialog
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.CategoryIconAdapter
import com.lawlett.financeapp.databinding.FragmentChangeBalanceSheetDialogBinding
import com.lawlett.financeapp.presenter.ChangeBalancePresenter
import com.lawlett.view.ChangeBalanceView
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ChangeBalanceSheetDialogFragment :
    BaseBottomSheetDialog<FragmentChangeBalanceSheetDialogBinding>(
        FragmentChangeBalanceSheetDialogBinding::inflate
    ), ChangeBalanceView {

    @InjectPresenter
    lateinit var presenter: ChangeBalancePresenter

    @Inject
    lateinit var hiltPresenter: ChangeBalancePresenter

    var icon = 0
    var iconName = ""

    @ProvidePresenter
    fun providePresenter(): ChangeBalancePresenter {
        return hiltPresenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = CategoryIconAdapter(hiltPresenter.getCategoryIcon())
        binding.categoryRecycler.adapter = adapter
        adapter.setOnItemClickListener { _, view, position ->
            icon = hiltPresenter.getCategoryIcon()[position].icon
            iconName = hiltPresenter.getCategoryIcon()[position].name
        }
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
        with(binding) {
            val amount = binding.amountEd.text.toString()
            when (tag) {
                getString(R.string.cost) -> {

                }
                getString(R.string.income) -> {
                    applyBtn.setOnClickListener {
                        hiltPresenter.saveIncome(BalanceModel(income = amount, icon = icon, iconName = iconName, isIncome = true, date = "date"))
                    }
                }
            }
        }
    }

    override fun closeDialog() {
        dismiss()
    }
}