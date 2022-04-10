package com.lawlett.domain.usecase

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel

class GetIncomeUseCase(private val repository: BalanceRepository) {

     fun getIncome(): BalanceModel = repository.getIncome()

}