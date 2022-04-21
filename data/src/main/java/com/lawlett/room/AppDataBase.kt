package com.lawlett.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lawlett.room.dao.*
import com.lawlett.room.model.BalanceRoomModel
import com.lawlett.room.model.MonthPlanRoomModel
import com.lawlett.room.model.PlanRoomModel

@Database(
    entities = [BalanceRoomModel::class, PlanRoomModel::class,
        MonthPlanRoomModel::class],
    version = 5,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun balanceDao(): BalanceDao

    abstract fun categoryDao(): CategoryDao

    abstract fun monthDao(): MonthDao

    abstract fun planDao(): PlanDao

    abstract fun monthPlanDao(): PlanMonthDao

}