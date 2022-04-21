package com.lawlett.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lawlett.ext.MONTH_PLAN_ROOM

@Entity(tableName = MONTH_PLAN_ROOM)
data class MonthPlanRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val month: String,
    val date: String,
    val amount: String,
    val day: String,
    val isMonth: Boolean
)
