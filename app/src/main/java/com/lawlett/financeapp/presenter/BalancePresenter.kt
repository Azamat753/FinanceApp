package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.usecase.balance.*
import com.lawlett.view.BalanceView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BalancePresenter @Inject constructor(
    private val getIncomeUseCase: GetIncomeUseCase,
    private val getIncomeListUseCase: GetIncomeListUseCase,
    private val getCostListUseCase: GetCostListUseCase,
    private val createCostUseCase: CreateCostUseCase
) : BasePresenter<BalanceView>() {

    fun getIncome(): BalanceModel = getIncomeUseCase.getIncome()

    fun getIncomeList(): List<BalanceModel> = getIncomeListUseCase.getIncomeList()

    fun getCostList(): List<BalanceModel> = getCostListUseCase.getCostList()

    fun checkData(costList: List<BalanceModel>, incomeList: List<BalanceModel>) {
        if (costList.isEmpty() && incomeList.isEmpty()) viewState.emptyData()
    }


    fun initData() = viewState.initData()

    fun checkCostList(costList: List<BalanceModel>) {
        if (costList.isNotEmpty())
            viewState.txtCost()
    }


    fun checkIncomeList(incomeList: List<BalanceModel>) {
        if (incomeList.isNotEmpty()) viewState.txtIncome()
    }


    fun createCost(amount: String, icon: Int, iconName: String, date: String, month: String) {
        createCostUseCase.createCost(
            BalanceModel(
                balance = "0",
                income = "0",
                cost = amount,
                icon = icon,
                iconName = iconName,
                date = date,
                month = month
            )
        )
        initData()
    }
}




