package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.CheckModelToPlan
import com.lawlett.domain.usecase.plan.CreateMonthPlanUseCase
import com.lawlett.view.AddMonthView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class AddMonthPresenter @Inject constructor(
    private val createMonthPlanUseCase: CreateMonthPlanUseCase,
) : BasePresenter<AddMonthView>() {

    fun createPlan(
        date: String,
        month: String,
        amount: String,
        day: String,
        isMonth: Boolean
    ) = checkModel(createMonthPlanUseCase.createPlan(date, month, amount, day, isMonth))

    private fun checkModel(createPlan: CheckModelToPlan) {
        with(createPlan) {
            when {
                isBlankToDate -> viewState.notEmptyDate()
                isBlankToAmount -> viewState.notEmptyAmount()
                isNegative -> viewState.notNegative()
                isZero -> viewState.notZero()
                isSuccess -> viewState.closeDialog()
            }
        }
    }
}