package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.usecase.FillIconsUseCase
import com.lawlett.domain.usecase.SaveIncomeUseCase
import com.lawlett.view.ChangeBalanceView
import moxy.InjectViewState
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class ChangeBalancePresenter @Inject constructor(
    private val fillIconsUseCase: FillIconsUseCase,
    private val saveIncomeUseCase: SaveIncomeUseCase
) : BasePresenter<ChangeBalanceView>() {


    fun getCategoryIcon(): ArrayList<CategoryIconModel> {
        return fillIconsUseCase.fillIcons()
    }

    fun saveIncome(model: BalanceModel) {
        saveIncomeUseCase.saveIncome(model)
        viewState.closeDialog()
    }
}