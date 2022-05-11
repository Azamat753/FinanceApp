package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.CheckModelToPlan
import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.model.TempPlanModel
import com.lawlett.domain.usecase.plan.CreatePlanUseCase
import com.lawlett.domain.usecase.plan.GetMonthPlanUseCase
import com.lawlett.domain.usecase.plan.GetPlanListUseCase
import com.lawlett.view.PlanView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class PlanPresenter @Inject constructor(
    private val getPlanListUseCase: GetPlanListUseCase,
    private val createPlanUseCase: CreatePlanUseCase,
    private val getMonthPlanUseCase: GetMonthPlanUseCase
) : BasePresenter<PlanView>() {

    fun initData() = viewState.initData()

    fun getModel(): TempPlanModel =
        getPlanListUseCase.getPlanList()

    fun checkModel(tempPlanModel: TempPlanModel) =
        if (tempPlanModel.monthList.isEmpty())
            viewState.emptyData()
        else
            viewState.initPlanVisible()



    fun createPlan(
        date: String,
        month: String, amount: String,
        source: String,
        day: String,
        id: Int
    ) {
        checkModel(createPlanUseCase.createPlan(date, month, amount, source, day, id))
        initData()
    }

    private fun checkModel(createPlan: CheckModelToPlan) {
        with(createPlan) {
            when {
                isBlankToDate -> viewState.notEmptyDate()
                isBlankToAmount -> viewState.notEmptyAmount()
                isNegative -> viewState.notNegative()
                isExpectedToDate -> viewState.notExpectedDate()
                isBlankToSource -> viewState.notEmptySource()
                isZero -> viewState.notZero()
                isSuccess -> viewState.closeDialog()
            }
        }
    }

    fun getPlanList(id: Int): List<PlanModel> = getMonthPlanUseCase.getListMonth(id)
}