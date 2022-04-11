package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.usecase.FillCostIconUseCase
import com.lawlett.domain.usecase.FillIconsUseCase
import com.lawlett.domain.usecase.SaveCostUseCase
import com.lawlett.domain.usecase.SaveIncomeUseCase
import com.lawlett.financeapp.utils.checkIcon
import com.lawlett.financeapp.utils.checkNumber
import com.lawlett.financeapp.utils.checkNumberToZero
import com.lawlett.view.ChangeBalanceView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class ChangeBalancePresenter @Inject constructor(
    private val fillIconsUseCase: FillIconsUseCase,
    private val fillCostIconUseCase: FillCostIconUseCase,
    private val saveCostUseCase: SaveCostUseCase,
    private val saveIncomeUseCase: SaveIncomeUseCase
) : BasePresenter<ChangeBalanceView>() {


    fun getCategoryIcon(): List<CategoryIconModel> = fillIconsUseCase.fillIncome()
    fun getCostIcon(): List<CategoryIconModel> = fillCostIconUseCase.fillCost()

    fun createCost(amount: String, icon: Int, iconName: String, date: String, month: String) {
        when {
            amount.isBlank() -> viewState.notEmpty()
            !checkNumber(amount.toInt()) -> viewState.notNegative()
            !checkNumberToZero(amount.toInt()) -> viewState.notZero()
            amount.subSequence(0, 1) == "0" -> viewState.notZero()
            !checkIcon(icon) -> viewState.notIcon()
            else -> {
                saveCostUseCase.saveCost(amount, icon, iconName, date, month)
                viewState.closeDialog()
            }
        }
    }

    fun createIncome(amount: String, icon: Int, iconName: String, date: String, month: String) {
        when {
            amount.isBlank() -> viewState.notEmpty()
            !checkNumber(amount.toInt()) -> viewState.notNegative()
            !checkNumberToZero(amount.toInt()) -> viewState.notZero()
            amount.subSequence(0, 1) == "0" -> viewState.notZero()
            !checkIcon(icon) -> viewState.notIcon()
            else -> {
                saveIncomeUseCase.saveIncome(amount, icon, iconName, date, month)
                viewState.closeDialog()
            }
        }
    }
}

