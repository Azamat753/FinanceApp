package com.lawlett.domain.usecase.balance

import com.lawlett.domain.model.CheckModelToBalance
import com.lawlett.domain.repo.BalanceRepository

class SaveIncomeUseCase(private val repository: BalanceRepository) {

    fun saveIncome(
        amount: String, icon: Int, iconName: String, date: String, month: String,
    ): CheckModelToBalance =
        repository.saveIncome(
            amount, icon, iconName, date, month
        )
}