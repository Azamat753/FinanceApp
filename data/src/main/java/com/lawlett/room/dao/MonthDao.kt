package com.lawlett.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.lawlett.room.model.BalanceRoomModel

@Dao
interface MonthDao {
    @Query("SELECT month FROM balanceModel")
    suspend fun getListMonth(): List<String>

    @Query("SELECT * FROM balanceModel WHERE month =:month")
    suspend fun getListToMonth(month: String): List<BalanceRoomModel>

    @Query("SELECT * FROM balanceModel")
    suspend fun getAllList(): List<BalanceRoomModel>
}