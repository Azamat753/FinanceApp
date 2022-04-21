package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.model.PlanMonthModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.PlanAdapter
import com.lawlett.financeapp.databinding.FragmentPlanBinding
import com.lawlett.financeapp.dialog.PlanDialog
import com.lawlett.financeapp.presenter.PlanPresenter
import com.lawlett.financeapp.sheetdialog.AddMonthSheetDialog
import com.lawlett.view.PlanView
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import java.util.Collections.reverse
import javax.inject.Inject

@AndroidEntryPoint
class PlanFragment : MvpAppCompatFragment(R.layout.fragment_plan), PlanView, PlanDialog.Result,
    PlanAdapter.ResultA, AddMonthSheetDialog.Result {
    private val binding: FragmentPlanBinding by viewBinding()

    private lateinit var planAdapter: PlanAdapter

    private lateinit var planDialog: PlanDialog

    @Inject
    lateinit var hiltPresenter: PlanPresenter

    @InjectPresenter
    lateinit var presenter: PlanPresenter

    @ProvidePresenter
    fun providePresenter(): PlanPresenter {
        return hiltPresenter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
        initPresentData()
        initAdapter()
        initBtn()
    }

    private fun initDialog() {
        planDialog = PlanDialog(requireContext(), requireActivity(), this)
    }

    private fun initPresentData() = presenter.initData()


    private fun initAdapter() {
        planAdapter = PlanAdapter(this)
        binding.rvPlan.adapter = planAdapter
    }

    private fun initBtn() {
        binding.btnAddMonth.setOnClickListener {
            AddMonthSheetDialog(this).show(requireActivity().supportFragmentManager, "Lol")
        }
    }

    override fun initData() {
        addModelAdapter(
            presenter.getModel().monthList,
            presenter.getModel().nowAmount, presenter.getModel().lacksAmount
        )
    }

    private fun addModelAdapter(
        monthList: List<PlanMonthModel>,
        nowAmountList: List<String>,
        lackAmountList: List<String>
    ) {
        planAdapter.addModel(monthList, nowAmountList, lackAmountList)
    }

    override fun closeDialog() {
        planDialog.dismiss()
    }

    override fun notZero() {
        toast(getString(R.string.number_not_zero))
    }

    private fun toast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun notNegative() {
        toast(getString(R.string.not_negative_number))
    }

    override fun notEmptyAmount() {
        toast(getString(R.string.enter_amount))
    }

    override fun notEmptyDate() {
        toast(getString(R.string.specify_date))
    }

    override fun notEmptySource() {
        toast(getString(R.string.enter_source))
    }

    override fun notExpectedDate() {
        toast(getString(R.string.date_expected))
    }

    override fun transaction(
        date: String,
        month: String,
        amount: String,
        source: String,
        day: String,
        id: Int
    ) {
        presenter.createPlan(date, month, amount, source, day, id)
    }

    override fun translate(isMonth: Boolean, id: Int) {
        planDialog.initDialog(isMonth, id)
    }

    override fun getDay(id: Int): List<PlanModel> = presenter.getPlanList(id)

    override fun updateDate() = initPresentData()
}
