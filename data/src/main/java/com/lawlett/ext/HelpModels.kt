package com.lawlett.ext

import com.lawlett.domain.model.BalanceModel
import com.lawlett.room.model.BalanceRoomModel

fun BalanceModel.toRoomModel(): BalanceRoomModel {
    with(this) {
        return BalanceRoomModel(
            balance = balance!!,
            cost = cost!!,
            income = income!!,
            date = date!!,
            icon = icon!!,
            iconName = iconName!!,
            month = month!!
        )
    }
}

fun BalanceRoomModel.toModel(): BalanceModel {
    with(this) {
        return BalanceModel(balance, cost, income, date, icon, iconName, month)
    }
}

const val SUM_COST = "Общие расходы"

const val RESULT = "Итог"

fun checkIcon(number: Int): Boolean = number != 0


fun checkNumber(number: Int): Boolean = number >= 0

fun checkNumberToZero(number: Int): Boolean = number != 0