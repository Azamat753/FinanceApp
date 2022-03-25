package com.lawlett.room

import com.lawlett.room.model.BalanceRoomModel

interface BalanceDao {

    fun insertBalance()

    fun updateBalance(balanceRoomModel: BalanceRoomModel)

    fun getIncome():BalanceRoomModel
}