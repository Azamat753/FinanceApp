package com.lawlett.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balanceModel")
class BalanceRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val balance: String,
    val cost: String,
    val income: String,
    val date: String,
    val icon: Int,
    val iconName:String,
    val isIncome: Boolean
)