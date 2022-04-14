package com.lawlett.view

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SingleStateStrategy::class)
interface MonthView : MvpView {
    fun initData()
    fun txtGone()
    fun txtVisible()
}