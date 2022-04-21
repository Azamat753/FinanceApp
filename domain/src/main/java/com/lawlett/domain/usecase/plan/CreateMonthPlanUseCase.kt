package com.lawlett.domain.usecase.plan

import com.lawlett.domain.model.CheckModelToPlan
import com.lawlett.domain.repo.PlanRepository
import javax.inject.Inject

class CreateMonthPlanUseCase @Inject constructor(private val repository: PlanRepository) {

    fun createPlan(
        date: String,
        month: String,
        amount: String,
        day: String,
        isMonth:Boolean
    ): CheckModelToPlan = repository.createMonthPlan(date, month, amount, day, isMonth)
}