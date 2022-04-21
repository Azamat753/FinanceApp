package com.lawlett.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lawlett.ext.BALANCE_ROOM

@Entity(tableName = BALANCE_ROOM)
class BalanceRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val balance: String,
    val cost: String,
    val income: String,
    val date: String,
    val icon: Int,
    val iconName: String,
    val month: String
)