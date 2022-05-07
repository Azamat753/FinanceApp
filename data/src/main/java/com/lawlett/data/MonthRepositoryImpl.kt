package com.lawlett.data

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.repo.MonthRepository
import com.lawlett.room.dao.MonthDao
import com.lawlett.ext.RESULT
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MonthRepositoryImpl @Inject constructor(
    private val
    monthDao: MonthDao
) : MonthRepository {

    override fun gelAllMonth(): List<BalanceModel> {
        val balanceList: MutableList<BalanceModel> = mutableListOf()
        val list = HashSet<String>()
        var cost: Long
        var balance: Long
        var income: Long
        var costToString: String
        runBlocking {
            launch {
                if (monthDao.getListMonth().isNotEmpty()) {
                    for (item in monthDao.getListMonth()) {
                        list.add(item)
                    }
                }

                for (item in list) {
                    costToString = ""
                    cost = 0
                    income = 0
                    balance = 0
                    val temp = monthDao.getListToMonth(item)
                    for (lol in temp) {
                        cost = cost + lol.cost.toLong()
                        income = income + lol.income.toLong()
                    }
                    balance = income - cost
                    costToString = if (cost == 0.toLong()) {
                        cost.toString()
                    } else {
                        "-$cost"
                    }
                    balanceList.add(
                        BalanceModel(
                            month = item,
                            balance = balance.toString(),
                            cost = costToString
                        )
                    )
                }
                if (getModel().cost != null)
                    if (getModel().cost != "0")
                        balanceList.add(getModel())
            }
        }
        return balanceList
    }

    private fun getModel(): BalanceModel {
        var model = BalanceModel()
        var cost: Long = 0
        var balance: Long
        var income: Long = 0
        var costToString: String
        runBlocking {
            launch {
                if (monthDao.getAllList().isNotEmpty()) {
                    for (item in monthDao.getAllList()) {
                        cost = cost + item.cost.toLong()
                        income = income + item.income.toLong()
                    }
                    balance = income - cost
                    costToString = if (cost == 0.toLong()) {
                        cost.toString()
                    } else {
                        "-$cost"
                    }
                    model = BalanceModel(
                        month = RESULT,
                        balance = balance.toString(),
                        cost = costToString
                    )
                }
            }
        }
        return model
    }
}