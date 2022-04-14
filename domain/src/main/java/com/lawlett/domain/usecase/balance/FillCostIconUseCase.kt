package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.CategoryIconModel
import javax.inject.Inject

class FillCostIconUseCase @Inject constructor(private val repository: BalanceRepository) {
    fun fillCost(): List<CategoryIconModel> = repository.fillCost()
}