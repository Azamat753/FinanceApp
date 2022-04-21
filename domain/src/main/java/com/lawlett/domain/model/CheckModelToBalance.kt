package com.lawlett.domain.model

data class CheckModelToBalance(
    val isBlank: Boolean = false,
    val isZero: Boolean = false,
    val isNegative: Boolean = false,
    val isWarning: Boolean = false,
    val isIcon: Boolean = false,
    val isSuccess: Boolean = false,
)
