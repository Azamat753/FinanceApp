package com.lawlett.domain.usecase

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.CategoryIconModel
import javax.inject.Inject

class FillCostIconUseCase @Inject constructor(private val repository: BalanceRepository) {
    fun fillCost(): List<CategoryIconModel> = repository.fillCost()
}