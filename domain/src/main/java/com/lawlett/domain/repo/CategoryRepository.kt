package com.lawlett.domain.repo

import com.lawlett.domain.model.BalanceModel

interface CategoryRepository {

    fun getAllCategory(): List<BalanceModel>
}