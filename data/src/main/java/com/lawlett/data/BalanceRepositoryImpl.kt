package com.lawlett.data

import com.lawlett.domain.repo.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.domain.model.CheckModelToBalance
import com.lawlett.ext.*
import com.lawlett.room.dao.BalanceDao
import com.lawlett.room.model.BalanceRoomModel
import kotlinx.coroutines.*
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val balanceDao: BalanceDao
) : BalanceRepository {

    override fun saveIncome(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String
    ): CheckModelToBalance {
        var isBlank = false
        var isZero = false
        var isNegative = false
        var isIcon = false
        var isSuccess = false
        runBlocking {
            launch {
                when {
                    amount.isBlank() -> isBlank = true
                    !checkNumber(amount.toInt()) -> isNegative = true
                    !checkNumberToZero(amount.toInt()) -> isZero = true
                    amount.subSequence(0, 1) == "0" -> isZero = true
                    !checkIcon(icon) -> isIcon = true
                    else -> {
                        isSuccess = true
                        balanceDao.insertBalance(
                            BalanceModel
                                (
                                balance = "0",
                                cost = "0",
                                icon = icon,
                                iconName = iconName,
                                date = date,
                                month = month,
                                income = amount
                            ).toRoomModel()
                        )
                    }
                }
            }
        }
        return CheckModelToBalance(
            isBlank = isBlank, isZero = isZero,
            isNegative = isNegative, isIcon = isIcon,
            isSuccess = isSuccess
        )
    }

    override fun saveCost(
        amount: String,
        icon: Int,
        iconName: String,
        date: String,
        month: String,
        balanceModel: BalanceModel
    ): CheckModelToBalance {
        var isBlank = false
        var isZero = false
        var isNegative = false
        var isWarning = false
        var isIcon = false
        var isSuccess = false
        runBlocking {
            launch {
                when {
                    amount.isBlank() -> isBlank = true
                    !checkNumber(amount.toInt()) -> isNegative = true
                    !checkNumberToZero(amount.toInt()) -> isZero = true
                    amount.subSequence(0, 1) == "0" -> isZero = true
                    !checkIcon(icon) -> isIcon = true
                    balanceModel.balance?.toInt()!! < amount.toInt() -> isWarning = true
                    else -> {
                        isSuccess = true
                        balanceDao.insertBalance(
                            BalanceRoomModel(
                                balance = "0",
                                cost = amount,
                                icon = icon,
                                iconName = iconName,
                                date = date,
                                month = month,
                                income = "0"
                            )
                        )
                    }
                }
            }
        }
        return CheckModelToBalance(
            isBlank,
            isZero, isNegative, isWarning, isIcon, isSuccess
        )
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

}

