package com.lawlett.domain.usecase.plan

import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.repo.PlanRepository
import javax.inject.Inject

class GetMonthPlanUseCase @Inject constructor(private val repository: PlanRepository) {
    fun getListMonth(id: Int): List<PlanModel> = repository.getPlanToId(id)
}