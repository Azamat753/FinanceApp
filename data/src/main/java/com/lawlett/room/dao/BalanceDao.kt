package com.lawlett.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lawlett.room.model.BalanceRoomModel
import java.util.concurrent.Flow

@Dao
interface BalanceDao {

    @Insert
    suspend fun insertBalance(balanceRoomModel: BalanceRoomModel)

    @Update
    suspend fun updateBalance(balanceRoomModel: BalanceRoomModel)

    @Query("SELECT * FROM balanceModel")
    fun getIncome(): kotlinx.coroutines.flow.Flow<List<BalanceRoomModel>>
}