package com.lawlett.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ChangeBalanceView : MvpView {
    fun closeDialog()
    fun notZero()
    fun notNegative()
    fun notIcon()
    fun notEmpty()
}