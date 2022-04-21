package com.lawlett.domain.usecase.plan

import com.lawlett.domain.model.CheckModelToPlan
import com.lawlett.domain.repo.PlanRepository
import javax.inject.Inject

class CreatePlanUseCase @Inject constructor(
    private val repository: PlanRepository
) {
    fun createPlan(
        date: String,
        month: String,
        amount: String,
        source: String,
        day: String,
        id: Int
    ): CheckModelToPlan = repository.createPlan(
        date, month, amount, source,
        day, id
    )
}