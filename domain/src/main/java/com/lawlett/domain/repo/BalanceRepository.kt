package com.lawlett.domain.repo

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.model.CheckModelToBalance

interface BalanceRepository {
    fun saveIncome(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ): CheckModelToBalance

    fun saveCost(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String, balanceModel: BalanceModel
    ): CheckModelToBalance

    fun getIncome(): BalanceModel
    fun getCostList(): List<BalanceModel>
    fun fillIncome(): List<CategoryIconModel>
    fun fillCost(): List<CategoryIconModel>
    fun getIncomeList(): List<BalanceModel>
    fun saveCost(balanceModel: BalanceModel)
}