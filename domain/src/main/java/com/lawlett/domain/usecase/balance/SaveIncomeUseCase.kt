package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.BalanceModel

class SaveIncomeUseCase(private val repository: BalanceRepository) {

    fun saveIncome(
        amount: String, icon: Int, iconName: String, date: String, month: String,
    ) {
        repository.saveIncome(
            BalanceModel(
                balance = "0",
                income = amount,
                icon = icon,
                iconName = iconName,
                date = date,
                cost = "0",
                month = month
            )
        )
    }

}