package com.lawlett.view

import com.lawlett.domain.model.CategoryIconModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.util.ArrayList

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ChangeBalanceView : MvpView {
    fun closeDialog()
}