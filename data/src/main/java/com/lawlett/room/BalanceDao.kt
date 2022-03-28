package com.lawlett.room

import com.lawlett.room.model.BalanceRoomModel
import java.util.concurrent.Flow

interface BalanceDao {

    fun insertBalance()

    fun updateBalance(balanceRoomModel: BalanceRoomModel)

    fun getIncome():BalanceRoomModel
}