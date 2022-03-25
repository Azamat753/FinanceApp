package com.lawlett.domain

import com.lawlett.domain.model.BalanceModel

interface BalanceRepository {
    fun saveIncome(balanceModel: BalanceModel)
    fun saveCost()
    fun getIncome(): BalanceModel
    fun getCost()
}