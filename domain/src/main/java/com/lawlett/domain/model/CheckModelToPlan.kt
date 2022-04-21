package com.lawlett.domain.model

data class CheckModelToPlan(
    val isSuccess: Boolean,
    val isBlankToAmount: Boolean,
    val isBlankToSource: Boolean,
    val isZero: Boolean,
    val isNegative: Boolean,
    val isBlankToDate: Boolean,
    val isExpectedToDate: Boolean
)