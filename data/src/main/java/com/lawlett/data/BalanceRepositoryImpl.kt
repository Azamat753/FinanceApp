package com.lawlett.data

import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.room.dao.BalanceDao
import com.lawlett.room.model.BalanceRoomModel
import kotlinx.coroutines.*
import javax.inject.Inject


class BalanceRepositoryImpl @Inject constructor(
    private val balanceDao: BalanceDao
) : BalanceRepository {

    override fun saveIncome(balanceModel: BalanceModel) {
        val model = balanceModel.toRoomModel()
        runBlocking {
            launch {
                balanceDao.insertBalance(model)
            }
        }
    }

    override fun saveCost(balanceModel: BalanceModel) {
        runBlocking {
            launch {
                balanceDao.insertBalance(balanceModel.toRoomModel())
            }
        }
    }

    override fun getIncome(): BalanceModel {
        var model = BalanceModel()
        var income = 0
        var cost = 0
        var balance: Int
        runBlocking {
            launch {
                val list = balanceDao.getAllList()
                if (list.isNotEmpty()) {
                    for (item in list) {
                        income = income + item.income.toInt()
                        cost = cost + item.cost.toInt()
                    }
                    balance = income - cost
                    model = BalanceModel(
                        income = income.toString(),
                        cost = cost.toString(),
                        balance = balance.toString()
                    )
                } else {
                    model = BalanceModel(
                        income = "0",
                        cost = "0",
                        balance = "0"
                    )
                }
            }
        }

        return model
    }

    override fun getCostList(): List<BalanceModel> {
        val list: MutableList<BalanceModel> = ArrayList()
        runBlocking {
            launch {
                for (item in balanceDao.getAllList()) {
                    if (item.cost != "0")
                        list.add(item.toModel())
                }
            }
        }
        return list
    }


    override fun fillIncome(): List<CategoryIconModel> {
        val listIcon = arrayListOf<CategoryIconModel>()
        with(listIcon) {
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Работа"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Образование"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Одежда"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Питание"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Транспорт"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Здоровье"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Развлечение"))
            add(CategoryIconModel(R.drawable.ic_briefcase_green, "Прочее"))
        }
        return listIcon
    }

    override fun fillCost(): List<CategoryIconModel> {
        val listIcon = arrayListOf<CategoryIconModel>()
        with(listIcon) {
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Работа"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Образование"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Одежда"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Питание"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Транспорт"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Здоровье"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Развлечение"))
            add(CategoryIconModel(R.drawable.ic_briefcase_red, "Прочее"))
        }
        return listIcon
    }

    override fun getIncomeList(): List<BalanceModel> {
        val list: MutableList<BalanceModel> = ArrayList()
        runBlocking {
            launch {
                for (item in balanceDao.getAllList()) {
                    if (item.income != "0")
                        list.add(item.toModel())
                }
            }
        }
        return list
    }

    private fun BalanceModel.toRoomModel(): BalanceRoomModel {
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

    private fun BalanceRoomModel.toModel(): BalanceModel {
        with(this) {
            return BalanceModel(balance, cost, income, date, icon, iconName, month)
        }
    }
}