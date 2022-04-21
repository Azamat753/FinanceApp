package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.view.View
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.CostAdapter
import com.lawlett.financeapp.adapter.IncomeAdapter
import com.lawlett.financeapp.databinding.FragmentBalanceBinding
import com.lawlett.financeapp.dialog.DialogWarning
import com.lawlett.financeapp.presenter.BalancePresenter
import com.lawlett.financeapp.sheetdialog.ChangeBalanceSheetDialogFragment
import com.lawlett.view.BalanceView
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.reverse
import javax.inject.Inject

@AndroidEntryPoint
class BalanceFragment : MvpAppCompatFragment(R.layout.fragment_balance), BalanceView,
    ChangeBalanceSheetDialogFragment.Result, DialogWarning.Result {
    private val binding: FragmentBalanceBinding by viewBinding()

    @InjectPresenter
    lateinit var presenter: BalancePresenter
    private lateinit var dayFormat: DateFormat
    private lateinit var currentDate: Date
    private lateinit var calendar: Calendar
    private var day = ""
    private var month = ""
    private lateinit var costAdapter: CostAdapter

    private lateinit var dialog: DialogWarning

    @Inject
    lateinit var hiltPresenter: BalancePresenter

    private lateinit var incomeAdapter: IncomeAdapter
    private var date = ""
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

    @ProvidePresenter
    fun providePresenter(): BalancePresenter {
        return hiltPresenter
    }

    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
        initModel()
        initAdapter()
        initClickers()
        initScroll()
        initTime()
        initData()
        initView()
    }

    private fun initDialog() {
        dialog = DialogWarning(requireActivity(), this)
    }


    private fun initView() {
        val temp = "$day $month"
        binding.dateTv.text = temp
    }

    private fun initModel() {
        initTxt(presenter.getIncome())
    }

    private fun initTxt(model: BalanceModel) {
        binding.balanceAmount.text = model.balance
        binding.costAmount.text = model.cost
        binding.incomeAmount.text = model.income
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

    private fun initScroll() {
        binding.costRecycler.isNestedScrollingEnabled = false
        binding.incomeRecycler.isNestedScrollingEnabled = false
    }

    private fun initAdapter() {
        costAdapter = CostAdapter()
        incomeAdapter = IncomeAdapter()
        binding.incomeRecycler.adapter = incomeAdapter
        binding.costRecycler.adapter = costAdapter
    }

    private fun initData() = presenter.initDate()

    private fun initClickers() {
        with(binding) {
            incomeLayout.setOnClickListener {
                ChangeBalanceSheetDialogFragment(this@BalanceFragment).show(
                    requireActivity().supportFragmentManager,
                    getString(R.string.income)
                )
            }
            costLayout.setOnClickListener {
                ChangeBalanceSheetDialogFragment(this@BalanceFragment).show(
                    requireActivity().supportFragmentManager,
                    getString(R.string.cost)
                )
            }
        }
    }

    override fun initDate() {
        initAdapterIncome(presenter.getIncomeList())
        initAdapterCost(presenter.getCostList())
        initModel()
        presenter.checkCostList(presenter.getCostList())
        presenter.checkIncomeList(presenter.getIncomeList())
    }

    override fun emptyIncome() {
        binding.incomeListTitle.text = getString(R.string.not_income)
    }

    override fun emptyCost() {
        binding.costListTitle.text = getString(R.string.not_cost)
    }

    override fun txtIncome() {
        binding.incomeListTitle.text = getString(R.string.income)
    }

    override fun txtCost() {
        binding.costListTitle.text = getString(R.string.cost)
    }

    override fun balanceNegative() = dialog.show()

    private fun initAdapterIncome(list: List<BalanceModel>) {
        reverse(list)
        incomeAdapter.addModel(list)
    }

    private fun initAdapterCost(list: List<BalanceModel>) {
        reverse(list)
        costAdapter.addModel(list)
    }

    override fun updateData() = initData()

    override fun getTempData(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ) = dialog.open(amount, icon, iconName, date, month)


    override fun confirm(amount: String, icon: Int, iconName: String, date: String, month: String) =
        presenter.createCost(amount, icon, iconName, date, month)
}