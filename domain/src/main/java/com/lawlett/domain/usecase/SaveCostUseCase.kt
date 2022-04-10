package com.lawlett.domain.usecase

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import javax.inject.Inject

class SaveCostUseCase @Inject constructor(private val repository: BalanceRepository) {
    fun saveCost(
        amount: String, icon: Int, iconName: String, date: String, month: String,

        ) {
        repository.saveCost(
            BalanceModel(
                balance = "0",
                income = "0",
                icon = icon,
                iconName = iconName,
                date = date,
                cost = amount,
                month = month
            )
        )
    }
}