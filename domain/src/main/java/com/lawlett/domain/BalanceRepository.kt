package com.lawlett.domain

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import java.util.ArrayList

interface BalanceRepository {
    fun saveIncome(balanceModel: BalanceModel)
    fun saveCost()
    fun getIncome(): BalanceModel
    fun getCost()
    fun fillIcons() : ArrayList<CategoryIconModel>
}