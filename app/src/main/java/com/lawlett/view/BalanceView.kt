package com.lawlett.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface BalanceView : MvpView {
    fun initData()
    fun emptyData()
    fun txtIncome()
    fun txtCost()
    fun balanceNegative()
}