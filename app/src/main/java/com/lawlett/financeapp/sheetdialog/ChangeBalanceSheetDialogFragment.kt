package com.lawlett.financeapp.sheetdialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.core.base.BaseBottomSheetDialog
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.CategoryAdapter
import com.lawlett.financeapp.databinding.FragmentChangeBalanceSheetDialogBinding
import com.lawlett.financeapp.presenter.ChangeBalancePresenter
import com.lawlett.view.ChangeBalanceView
import dagger.hilt.android.AndroidEntryPoint
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ChangeBalanceSheetDialogFragment(private val result: Result) :
    BaseBottomSheetDialog<FragmentChangeBalanceSheetDialogBinding>(
        FragmentChangeBalanceSheetDialogBinding::inflate
    ), ChangeBalanceView, CategoryAdapter.Result {
    private var day = ""

    private var month = ""


    private var date = ""


    private lateinit var dayFormat: DateFormat

    private lateinit var currentDate: Date

    private lateinit var calendar: Calendar

    private val monthNames: Array<String>
        get() = arrayOf(
            "Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнь",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"
        )


    @Inject
    lateinit var hiltPresenter: ChangeBalancePresenter

    @InjectPresenter
    lateinit var presenter: ChangeBalancePresenter

    private lateinit var adapter: CategoryAdapter


    private var icon = 0
    private var iconName = ""

    @ProvidePresenter
    fun providePresenter(): ChangeBalancePresenter {
        return hiltPresenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTime()
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
        var amount: String
        with(binding) {
            when (tag) {
                getString(R.string.cost) -> {
                    applyBtn.setOnClickListener {
                        amount = amountEd.text.toString()
                        presenter.createCostToWarning(amount, icon, iconName, date, month)
                    }
                }
                getString(R.string.income) -> {
                    applyBtn.setOnClickListener {
                        amount = amountEd.text.toString()
                        presenter.createIncome(amount, icon, iconName, date, month)
                    }
                }
            }
        }
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun closeDialog() {
        dismiss()
        result.updateData()
    }

    override fun notZero() {
        toast(getString(R.string.number_not_zero))
    }

    override fun notNegative() {
        toast(getString(R.string.not_negative_number))
    }

    override fun notIcon() {
        toast(getString(R.string.choose_category))
    }

    override fun notEmpty() {
        toast(getString(R.string.enter_amount))
    }

    override fun negativeWarning(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ) {
        result.getTempData(amount, icon, iconName, date, month)
    }


    override fun click(name: String, icon: Int) {
        this.icon = icon
        iconName = name
    }

    private fun initTime() {
        currentDate = Date()
        calendar = Calendar.getInstance()
        dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val yearFormat: DateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        month = monthNames[calendar[Calendar.MONTH]]
        day = dayFormat.format(currentDate)
        val year = yearFormat.format(currentDate)
        date = "$day $month $year"
    }

    interface Result {
        fun updateData()
        fun getTempData(
            amount: String,
            icon: Int,
            iconName: String,
            date: String,
            month: String
        )
    }

}