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
    private val saveIncomeUseCase: SaveIncomeUseCase,
    private val saveCostUseCase: SaveCostUseCase,
    private val getCostListUseCase: GetCostListUseCase
) : BasePresenter<BalanceView>() {

    fun getIncome(): BalanceModel = getIncomeUseCase.getIncome()

    fun getIncomeList(): List<BalanceModel> = getIncomeListUseCase.getIncomeList()

    fun getCostList(): List<BalanceModel> = getCostListUseCase.getCostList()

    fun saveIncome(
        amount: String, icon: Int, iconName: String, date: String, month: String
    ) {
        saveIncomeUseCase.saveIncome(amount, icon, iconName, date, month)
        initDate()
    }

    fun saveCost(amount: String, icon: Int, iconName: String, date: String, month: String) {
        saveCostUseCase.saveCost(amount, icon, iconName, date, month)
        initDate()
    }


    fun initDate() = viewState.initDate()

}

