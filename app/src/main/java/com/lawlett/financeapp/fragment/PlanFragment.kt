package com.lawlett.financeapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.get
import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.model.PlanMonthModel
import com.lawlett.financeapp.R
import com.lawlett.financeapp.adapter.PlanAdapter
import com.lawlett.financeapp.databinding.FragmentPlanBinding
import com.lawlett.financeapp.dialog.PlanDialog
import com.lawlett.financeapp.presenter.PlanPresenter
import com.lawlett.financeapp.sheetdialog.AddMonthSheetDialog
import com.lawlett.financeapp.utils.*
import com.lawlett.view.PlanView
import com.redmadrobot.extensions.viewbinding.viewBinding
import com.takusemba.spotlight.Target
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class PlanFragment : MvpAppCompatFragment(R.layout.fragment_plan), PlanView, PlanDialog.Result,
    PlanAdapter.ResultA, AddMonthSheetDialog.Result {

    private val binding: FragmentPlanBinding by viewBinding()

    private lateinit var planAdapter: PlanAdapter

    private lateinit var planDialog: PlanDialog

    @Inject
    lateinit var pref: Pref

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
        checkHint()
    }

    private fun checkHint() {
        if (!pref.isShowPlan()) {
            val target = ArrayList<Target>()
            val root = FrameLayout(requireContext())
            val first = layoutInflater.inflate(R.layout.layout_target, root)
            val view = View(requireContext())
            Handler(Looper.getMainLooper()).postDelayed({
                val firstSpot = setSpotLightTarget(
                    view, first, "\n\n\n ${getString(R.string.now_plan)}" +
                            "\n\n${getString(R.string.window_for_plan)}" +
                            "\n${getString(R.string.main_fun_plan)}"
                )
                val secondSpot = setSpotLightTarget(
                    binding.btnAddMonth, first, "\n\n\n\n\n" +
                            " ${getString(R.string.click_for_month_plan)}"
                )
                target.add(firstSpot)
                target.add(secondSpot)
                setSpotLightBuilder(requireActivity(), target, first)
            }, 100)
            pref.showPlan()
        }
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
        presenter.checkModel(presenter.getModel())
        addModelAdapter(
            presenter.getModel().monthList,
            presenter.getModel().nowAmount,
            presenter.getModel().lacksAmount
        )
    }

    private fun addModelAdapter(
        monthList: List<PlanMonthModel>,
        nowAmountList: List<String>,
        lackAmountList: List<String>
    ) = planAdapter.addModel(monthList, nowAmountList, lackAmountList)

    override fun closeDialog() =
        planDialog.dismiss()


    override fun notZero() =
        toast(getString(R.string.number_not_zero))


    private fun toast(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun notNegative() =
        toast(getString(R.string.not_negative_number))


    override fun notEmptyAmount() =
        toast(getString(R.string.enter_amount))


    override fun notEmptyDate() =
        toast(getString(R.string.specify_date))


    override fun notEmptySource() =
        toast(getString(R.string.enter_source))


    override fun notExpectedDate() =
        toast(getString(R.string.date_expected))


    override fun emptyData() = binding.includeEmpty.root.visible()

    override fun initPlanVisible() {
        binding.includeEmpty.root.gone()
        checkHintToItem()
    }

    private fun checkHintToItem() {
        if(!pref.isShowCardPlan()){
            val target = ArrayList<Target>()
            val root = FrameLayout(requireContext())
            val first = layoutInflater.inflate(R.layout.layout_target, root)
            Handler(Looper.getMainLooper()).postDelayed({
                val firstSpot = setSpotLightTarget(
                    binding.rvPlan[0], first, "\n\n\n\n\n\n\n\n\n\n\n ${getString(R.string.card_plan)}"
                )
                target.add(firstSpot)
                setSpotLightBuilder(requireActivity(), target, first)
            }, 1000)
            pref.showCardPlan()
        }
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
