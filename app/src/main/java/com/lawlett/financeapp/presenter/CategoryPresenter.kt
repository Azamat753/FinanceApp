package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.usecase.category.GetAllCategoryUseCase
import com.lawlett.view.CategoryView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CategoryPresenter @Inject constructor(
    private val getAllCategoryUseCase: GetAllCategoryUseCase
) : BasePresenter<CategoryView>() {
    fun getAllCategoryList(): List<BalanceModel> = getAllCategoryUseCase.getAllCategoryList()

    fun initData() = viewState.initData()

    fun checkList(list: List<BalanceModel>) =
        if (list.isEmpty()) viewState.txtVisible() else viewState.txtGone()
}