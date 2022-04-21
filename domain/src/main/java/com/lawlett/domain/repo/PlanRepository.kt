package com.lawlett.domain.repo

import com.lawlett.domain.model.CheckModelToPlan
import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.model.TempPlanModel


interface PlanRepository {
    fun createPlan(
        date: String,
        month: String,
        amount: String,
        source: String,
        day: String,
        id: Int
    ): CheckModelToPlan

    fun getPlanList(): TempPlanModel

    fun createMonthPlan(
        date: String,
        month: String,
        amount: String,
        day: String,
        isMonth: Boolean
    ): CheckModelToPlan


    fun getPlanToId(id: Int): List<PlanModel>

}