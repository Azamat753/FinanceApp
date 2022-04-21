package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.model.CheckModelToBalance
import com.lawlett.domain.usecase.balance.*
import com.lawlett.view.ChangeBalanceView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ChangeBalancePresenter @Inject constructor(
    private val fillIconsUseCase: FillIconsUseCase,
    private val fillCostIconUseCase: FillCostIconUseCase,
    private val saveCostUseCase: SaveCostUseCase,
    private val saveIncomeUseCase: SaveIncomeUseCase,
    private val getIncomeUseCase: GetIncomeUseCase,
) : BasePresenter<ChangeBalanceView>() {


    fun getCategoryIcon(): List<CategoryIconModel> = fillIconsUseCase.fillIncome()
    fun getCostIcon(): List<CategoryIconModel> = fillCostIconUseCase.fillCost()

    fun createCostToWarning(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ) {
        checkDataToCost(
            saveCostUseCase.saveCost(
                amount, icon, iconName, date, month,
                getIncomeUseCase.getIncome()
            ),
            amount, icon, iconName, date, month
        )
    }

    private fun checkDataToCost(
        checkModel: CheckModelToBalance, amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ) {
        with(checkModel) {
            when {
                isBlank -> viewState.notEmpty()
                isWarning -> {
                    viewState.negativeWarning(amount, icon, iconName, date, month)
                    viewState.closeDialog()
                }
                isIcon -> viewState.notIcon()
                isZero -> viewState.notZero()
                isNegative -> viewState.notNegative()
                isSuccess -> viewState.closeDialog()
            }
        }
    }

    fun createIncome(amount: String, icon: Int, iconName: String, date: String, month: String) {
        checkDataToCost(
            saveIncomeUseCase.saveIncome(amount, icon, iconName, date, month),
            amount, icon, iconName, date, month
        )
    }

}


