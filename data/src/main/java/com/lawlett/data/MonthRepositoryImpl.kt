package com.lawlett.data

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.repo.MonthRepository
import com.lawlett.room.dao.MonthDao
import com.lawlett.utils.RESULT
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
        var cost: Int
        var balance: Int
        var income: Int
        runBlocking {
            launch {
                    if (monthDao.getListMonth().isNotEmpty()) {
                        for (item in monthDao.getListMonth()) {
                            list.add(item)
                        }
                    }

                for (item in list) {
                    cost = 0
                    income = 0
                    balance = 0
                    val temp = monthDao.getListToMonth(item)
                    for (lol in temp) {
                        cost = cost + lol.cost.toInt()
                        income = income + lol.income.toInt()
                    }
                    balance = income - cost
                    balanceList.add(
                        BalanceModel(
                            month = item,
                            balance = balance.toString(),
                            cost = cost.toString()
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
        var cost = 0
        var balance: Int
        var income = 0
        runBlocking {
            launch {
                if (monthDao.getAllList().isNotEmpty()) {
                    for (item in monthDao.getAllList()) {
                        cost = cost + item.cost.toInt()
                        income = income + item.income.toInt()
                    }
                    balance = income - cost
                    model = BalanceModel(
                        month = RESULT,
                        balance = balance.toString(),
                        cost = cost.toString()
                    )
                }
            }
        }
        return model
    }
}