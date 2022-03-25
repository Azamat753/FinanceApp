package com.lawlett.domain.usecase

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel

class SaveIncomeUseCase(private val repository: BalanceRepository) {

    fun saveIncome(balanceModel: BalanceModel) {
        repository.saveIncome(balanceModel)
    }

}