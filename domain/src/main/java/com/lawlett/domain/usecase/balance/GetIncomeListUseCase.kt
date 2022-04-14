package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import javax.inject.Inject

class GetIncomeListUseCase @Inject constructor(private val repository: BalanceRepository) {

     fun getIncomeList(): List<BalanceModel> = repository.getIncomeList()
}