package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.BalanceModel

class GetIncomeUseCase(private val repository: BalanceRepository) {

     fun getIncome(): BalanceModel = repository.getIncome()

}