package com.lawlett.domain.usecase.plan

import com.lawlett.domain.model.TempPlanModel
import com.lawlett.domain.repo.PlanRepository
import javax.inject.Inject

class GetPlanListUseCase @Inject constructor(
    private val repository: PlanRepository
) {
    fun getPlanList(): TempPlanModel = repository.getPlanList()
}