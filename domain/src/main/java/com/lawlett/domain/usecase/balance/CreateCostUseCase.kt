package com.lawlett.domain.usecase.balance

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.repo.BalanceRepository
import javax.inject.Inject

class CreateCostUseCase @Inject constructor(
    private val repository: BalanceRepository
) {
    fun createCost(balanceModel: BalanceModel) =
        repository.saveCost(balanceModel)
}