package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CheckModelToBalance
import javax.inject.Inject

class SaveCostUseCase @Inject constructor(private val repository: BalanceRepository) {
    fun saveCost(
        amount: String, icon: Int, iconName: String, date: String, month: String,
        balanceModel: BalanceModel
    ): CheckModelToBalance =
        repository.saveCost(
            amount, icon, iconName, date, month,
            balanceModel
        )

}