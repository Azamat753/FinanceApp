package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.usecase.month.GetMonthUseCase
import com.lawlett.view.MonthView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MonthPresenter @Inject constructor(
    private val getMonthUseCase: GetMonthUseCase
) : BasePresenter<MonthView>() {
    fun getMonthList(): List<BalanceModel> = getMonthUseCase.getMonthList()

    fun initData() = viewState.initData()

    fun checkList(list: List<BalanceModel>) =
        if (list.isEmpty()) viewState.txtVisible() else viewState.txtGone()
}