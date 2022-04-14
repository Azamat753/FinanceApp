package com.lawlett.domain.usecase.balance

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.CategoryIconModel
import javax.inject.Inject

class FillIconsUseCase @Inject constructor(private val repository: BalanceRepository) {

    fun fillIncome(): List<CategoryIconModel> = repository.fillIncome()
}