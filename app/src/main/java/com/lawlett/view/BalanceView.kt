package com.lawlett.view

import com.lawlett.domain.model.BalanceModel

interface BalanceView {
    fun getIncome(): BalanceModel
}