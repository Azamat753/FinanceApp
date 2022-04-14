package com.lawlett.domain.repo

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel

interface BalanceRepository {
    fun saveIncome(balanceModel: BalanceModel)
    fun saveCost(balanceModel: BalanceModel)
    fun getIncome(): BalanceModel
    fun getCostList(): List<BalanceModel>
    fun fillIncome(): List<CategoryIconModel>
    fun fillCost(): List<CategoryIconModel>
    fun getIncomeList(): List<BalanceModel>
}