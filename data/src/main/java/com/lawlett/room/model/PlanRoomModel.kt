package com.lawlett.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lawlett.ext.PLAN_ROOM

@Entity(tableName = PLAN_ROOM)
data class PlanRoomModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: String,
    val source: String,
    val date: String,
    val month: String,
    val day: String,
    val idMonth: Int
)
