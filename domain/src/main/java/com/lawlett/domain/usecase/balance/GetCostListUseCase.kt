package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import javax.inject.Inject

class GetCostListUseCase @Inject constructor(private val repository: BalanceRepository) {
    fun getCostList(): List<BalanceModel> = repository.getCostList()
}