package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.usecase.*
import com.lawlett.view.BalanceView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BalancePresenter @Inject constructor(
    private val getIncomeUseCase: GetIncomeUseCase,
    private val getIncomeListUseCase: GetIncomeListUseCase,
    private val getCostListUseCase: GetCostListUseCase
) : BasePresenter<BalanceView>() {

    fun getIncome(): BalanceModel = getIncomeUseCase.getIncome()

    fun getIncomeList(): List<BalanceModel> = getIncomeListUseCase.getIncomeList()

    fun getCostList(): List<BalanceModel> = getCostListUseCase.getCostList()


    fun initDate() = viewState.initDate()

    fun checkCostList(costList: List<BalanceModel>) =
        if (costList.isEmpty()) viewState.emptyCost() else viewState.txtCost()

    fun checkIncomeList(incomeList: List<BalanceModel>) =
        if (incomeList.isEmpty()) viewState.emptyIncome() else viewState.txtIncome()
}


