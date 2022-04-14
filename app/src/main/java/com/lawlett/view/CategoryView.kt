package com.lawlett.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CategoryView : MvpView {
    fun initData()
    fun txtGone()
    fun txtVisible()
}