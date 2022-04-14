package com.lawlett.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.lawlett.room.model.BalanceRoomModel

@Dao
interface CategoryDao {
    @Query("SELECT iconName FROM balanceModel")
    suspend fun getListCategory(): List<String>

    @Query("SELECT * FROM balanceModel WHERE iconName =:category")
    suspend fun getListToCategory(category: String): List<BalanceRoomModel>

    @Query("SELECT * FROM balanceModel")
    suspend fun getAllList(): List<BalanceRoomModel>
}