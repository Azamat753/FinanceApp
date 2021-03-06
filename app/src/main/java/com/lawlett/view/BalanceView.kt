package com.lawlett.view

import com.lawlett.domain.model.BalanceModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface BalanceView :MvpView{
    fun closeDialog()
}