package com.lawlett.domain.usecase.balance

import com.lawlett.domain.model.CheckModel
import com.lawlett.domain.repo.BalanceRepository

class SaveIncomeUseCase(private val repository: BalanceRepository) {

    fun saveIncome(
        amount: String, icon: Int, iconName: String, date: String, month: String,
    ): CheckModel =
        repository.saveIncome(
            amount, icon, iconName, date, month
        )
}