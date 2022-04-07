package com.lawlett.domain.usecase

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import javax.inject.Inject

class FillIconsUseCase @Inject constructor(private val repository: BalanceRepository) {

    fun fillIcons() :ArrayList<CategoryIconModel> {
      return repository.fillIcons()
    }
}