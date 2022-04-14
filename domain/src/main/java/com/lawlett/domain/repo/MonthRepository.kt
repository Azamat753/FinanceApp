package com.lawlett.domain.repo

import com.lawlett.domain.model.BalanceModel

interface MonthRepository {
    fun gelAllMonth(): List<BalanceModel>
}