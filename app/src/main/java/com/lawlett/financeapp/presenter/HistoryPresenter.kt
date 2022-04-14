package com.lawlett.financeapp.presenter

import com.example.core.base.BasePresenter
import com.lawlett.view.HistoryView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class HistoryPresenter
@Inject constructor() :
    BasePresenter<HistoryView>() {

    fun getData() = viewState.getData()
}