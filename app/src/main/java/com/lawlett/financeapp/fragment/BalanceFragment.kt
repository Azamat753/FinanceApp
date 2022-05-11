package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import com.lawlett.domain.model.BalanceModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.CostAdapter
import com.lawlett.financeapp.adapter.IncomeAdapter
import com.lawlett.financeapp.databinding.FragmentBalanceBinding
import com.lawlett.financeapp.dialog.DialogWarning
import com.lawlett.financeapp.presenter.BalancePresenter
import com.lawlett.financeapp.sheetdialog.ChangeBalanceSheetDialogFragment
import com.lawlett.financeapp.utils.*
import com.lawlett.view.BalanceView
import com.redmadrobot.extensions.viewbinding.viewBinding
import com.takusemba.spotlight.Target
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Collections.reverse
import javax.inject.Inject
import kotlin.collections.ArrayList

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

    @Inject
    lateinit var pref: Pref

    private lateinit var incomeAdapter: IncomeAdapter
    private var date = ""

    private val monthNames: ArrayList<String> by lazy {
        arrayListOf(
            getString(R.string.january),
            getString(R.string.february),
            getString(R.string.march),
            getString(R.string.april),
            getString(R.string.may),
            getString(R.string.june),
            getString(R.string.July),
            getString(R.string.August),
            getString(R.string.september),
            getString(R.string.October),
            getString(R.string.november),
            getString(R.string.December)
        )
    }

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
        initTempData()
        initView()
    }

    private fun checkHint() {
        if (!pref.isShowBalance()) {
            val target = ArrayList<Target>()
            val root = FrameLayout(requireContext())
            val first = layoutInflater.inflate(R.layout.layout_target, root)
            val view = View(requireContext())
            Handler(Looper.getMainLooper()).postDelayed({
                val firstSpot = setSpotLightTarget(
                    view, first, " \n\n\n\n\n\n\n\n Баланс \n\n\n Окно для просмотра " +
                            "Баланса \n\n Так же для списка Доходов и Расходов  "
                )
                target.add(firstSpot)
                setSpotLightBuilder(requireActivity(), target, first)
            }, 1000)
        }
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

    private fun initTempData() = presenter.initData()

    override fun emptyData() =
        binding.includeEmpty.root.visible()


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

    override fun initData() {
        initModel()
        presenter.checkData(
            costList = presenter.getCostList(),
            incomeList = presenter.getIncomeList()
        )
        presenter.checkCostList(presenter.getCostList())
        presenter.checkIncomeList(presenter.getIncomeList())
    }


    override fun txtIncome() {
        binding.incomeListTitle.text = getString(R.string.income)
        initAdapterIncome(presenter.getIncomeList())
        binding.includeEmpty.root.gone()
    }

    override fun txtCost() {
        binding.costListTitle.text = getString(R.string.cost)
        initAdapterCost(presenter.getCostList())
        binding.includeEmpty.root.gone()
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