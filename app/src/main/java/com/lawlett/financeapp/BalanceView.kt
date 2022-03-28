package com.lawlett.financeapp

import com.lawlett.domain.model.BalanceModel

interface BalanceView {
    fun getIncome(): BalanceModel
}