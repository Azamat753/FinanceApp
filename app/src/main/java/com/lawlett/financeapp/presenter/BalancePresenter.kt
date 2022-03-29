package com.lawlett.financeapp.presenter

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.usecase.GetIncomeUseCase
import com.lawlett.domain.usecase.SaveIncomeUseCase
import com.lawlett.view.BalanceView

class BalancePresenter(
    private val getIncomeUseCase: GetIncomeUseCase,
    private val saveIncomeUseCase: SaveIncomeUseCase
) : BalanceView {


    override fun getIncome(): BalanceModel {
        return getIncomeUseCase.getIncome()
    }

    fun saveIncome(balanceModel: BalanceModel) {
        saveIncomeUseCase.saveIncome(balanceModel)
    }
}