package com.lawlett.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lawlett.room.dao.BalanceDao
import com.lawlett.room.model.BalanceRoomModel

@Database(entities = [BalanceRoomModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun balanceDao(): BalanceDao
}