package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.usecase.FillCostIconUseCase
import com.lawlett.domain.usecase.FillIconsUseCase
import com.lawlett.domain.usecase.SaveIncomeUseCase
import com.lawlett.view.ChangeBalanceView
import moxy.InjectViewState
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class ChangeBalancePresenter @Inject constructor(
    private val fillIconsUseCase: FillIconsUseCase,
    private val fillCostIconUseCase: FillCostIconUseCase
) : BasePresenter<ChangeBalanceView>() {


    fun getCategoryIcon(): List<CategoryIconModel> = fillIconsUseCase.fillIncome()
    fun getCostIcon(): List<CategoryIconModel> = fillCostIconUseCase.fillCost()


}