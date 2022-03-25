package com.lawlett.data

import com.lawlett.domain.model.BalanceModel
import com.lawlett.room.model.BalanceRoomModel

infix fun BalanceModel.toRoomModel(roomModel: BalanceModel): BalanceRoomModel {
    with(roomModel) {
        return BalanceRoomModel(balance, cost, income, date, icon, isIncome)
    }
}

infix fun BalanceRoomModel.toModel(model: BalanceRoomModel): BalanceModel {
    with(model) {
        return BalanceModel(balance, cost, income, date, icon, isIncome)
    }
}