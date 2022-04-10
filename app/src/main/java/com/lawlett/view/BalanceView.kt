package com.lawlett.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface BalanceView : MvpView {
    fun initDate()
    fun emptyIncome()
    fun emptyCost()
    fun txtIncome()
    fun txtCost()
}