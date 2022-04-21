package com.lawlett.view

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SingleStateStrategy::class)
interface AddMonthView : MvpView {
    fun closeDialog()
    fun notZero()
    fun notNegative()
    fun notEmptyAmount()
    fun notEmptyDate()
}