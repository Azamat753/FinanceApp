package com.lawlett.domain.usecase.month

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.repo.MonthRepository
import javax.inject.Inject

class GetMonthUseCase @Inject constructor
    (private val repository: MonthRepository) {

    fun getMonthList(): List<BalanceModel> = repository.gelAllMonth()
}