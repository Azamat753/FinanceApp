package com.lawlett.domain.model

data class TempPlanModel(
    val monthList: List<PlanMonthModel> = arrayListOf(),
    val lacksAmount: List<String> = arrayListOf(),
    val nowAmount: List<String> = arrayListOf()
)