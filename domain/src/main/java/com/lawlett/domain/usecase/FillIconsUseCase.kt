package com.lawlett.domain.usecase

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel

class FillIconsUseCase(private val repository: BalanceRepository) {

    fun fillIcons() :ArrayList<CategoryIconModel> {
      return repository.fillIcons()
    }
}