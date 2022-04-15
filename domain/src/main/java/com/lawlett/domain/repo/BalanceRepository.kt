package com.lawlett.domain.repo

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.model.CheckModel

interface BalanceRepository {
    fun saveIncome(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ): CheckModel

    fun saveCost(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String, balanceModel: BalanceModel
    ): CheckModel

    fun getIncome(): BalanceModel
    fun getCostList(): List<BalanceModel>
    fun fillIncome(): List<CategoryIconModel>
    fun fillCost(): List<CategoryIconModel>
    fun getIncomeList(): List<BalanceModel>
    fun saveCost(balanceModel: BalanceModel)
}