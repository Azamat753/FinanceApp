package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.usecase.GetIncomeUseCase
import com.lawlett.domain.usecase.SaveIncomeUseCase
import com.lawlett.view.BalanceView
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class BalancePresenter @Inject constructor(
    private val getIncomeUseCase: GetIncomeUseCase,
) : BasePresenter<BalanceView>() {

    fun getIncome(): BalanceModel {

    }
}