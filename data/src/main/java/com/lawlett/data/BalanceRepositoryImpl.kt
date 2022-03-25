package com.lawlett.data

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.room.BalanceDao

class BalanceRepositoryImpl(private val balanceDao: BalanceDao) : BalanceRepository {

    override fun saveIncome(balanceModel: BalanceModel) {
        val model = balanceModel toRoomModel balanceModel
        balanceDao.updateBalance(model)
    }

    override fun saveCost() {
        TODO("Not yet implemented")
    }

    override fun getIncome(): BalanceModel {
        return balanceDao.getIncome() toModel balanceDao.getIncome()
    }


    override fun getCost() {
        TODO("Not yet implemented")
    }
}