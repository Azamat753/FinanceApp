package com.lawlett.data

import com.example.core.R
import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.room.BalanceDao
import com.lawlett.room.model.BalanceRoomModel
import java.util.ArrayList

class BalanceRepositoryImpl(private val balanceDao: BalanceDao) : BalanceRepository {

    override fun saveIncome(balanceModel: BalanceModel) {
        val model = balanceModel toRoomModel balanceModel
        balanceDao.updateBalance(model)
    }

    override fun saveCost() {
        TODO("Not yet implemented")
    }

    override fun getIncome(): BalanceModel {
        return balanceDao.getIncome() toModel balanceDao.getIncome()
    }

    override fun getCost() {
        TODO("Not yet implemented")
    }

    override fun fillIcons(): ArrayList<CategoryIconModel> {
        val listIcon = arrayListOf<CategoryIconModel>()
        with(listIcon) {
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
            add(CategoryIconModel(R.drawable.ic_work, "Работа"))
        }
        return listIcon
    }

    private infix fun BalanceModel.toRoomModel(roomModel: BalanceModel): BalanceRoomModel {
        with(roomModel) {
            return BalanceRoomModel(balance, cost, income, date, icon, isIncome)
        }
    }

    private infix fun BalanceRoomModel.toModel(model: BalanceRoomModel): BalanceModel {
        with(model) {
            return BalanceModel(balance, cost, income, date, icon, isIncome)
        }
    }
}