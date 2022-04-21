package com.lawlett.domain.model

data class PlanMonthModel(
    val amount: String,
    val month: String,
    val date: String,
    val id: Int,
    val day: String,
    val isMonth: Boolean
)
