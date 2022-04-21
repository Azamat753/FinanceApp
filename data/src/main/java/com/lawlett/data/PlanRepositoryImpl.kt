package com.lawlett.data

import com.lawlett.domain.model.CheckModelToPlan
import com.lawlett.domain.model.PlanModel
import com.lawlett.domain.model.PlanMonthModel
import com.lawlett.domain.model.TempPlanModel
import com.lawlett.domain.repo.PlanRepository
import com.lawlett.ext.checkNumber
import com.lawlett.ext.checkNumberToZero
import com.lawlett.room.dao.PlanDao
import com.lawlett.room.dao.PlanMonthDao
import com.lawlett.room.model.MonthPlanRoomModel
import com.lawlett.room.model.PlanRoomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlanRepositoryImpl @Inject constructor(
    private val planDao: PlanDao,
    private val planMonthDao: PlanMonthDao
) : PlanRepository {
    override fun createPlan(
        date: String,
        month: String,
        amount: String,
        source: String,
        day: String,
        id: Int
    ): CheckModelToPlan {
        var isBlankToSource = false
        var isBlankToAmount = false
        var isZero = false
        var isNegative = false
        var isSuccess = false
        var isBlankToDate = false
        var isExpectedToDate = false
        runBlocking {
            launch {
                when {
                    amount.isBlank() -> isBlankToAmount = true
                    source.isBlank() -> isBlankToSource = true
                    !checkNumber(amount.toInt()) -> isNegative = true
                    !checkNumberToZero(amount.toInt()) -> isZero = true
                    amount.subSequence(0, 1) == "0" -> isZero = true
                    date.isBlank() -> isBlankToDate = true
                    checkDate(id, day) -> isExpectedToDate = true
                    else -> {
                        isSuccess = true
                        planDao.createPlan(
                            PlanRoomModel(
                                amount = amount,
                                month = month,
                                date = date,
                                source = source,
                                day = day,
                                idMonth = id
                            )
                        )
                    }
                }
            }
        }
        return CheckModelToPlan(
            isSuccess = isSuccess,
            isNegative = isNegative,
            isZero = isZero,
            isBlankToAmount = isBlankToAmount,
            isBlankToSource = isBlankToSource,
            isBlankToDate = isBlankToDate,
            isExpectedToDate = isExpectedToDate
        )
    }

    override fun getPlanList(): TempPlanModel {
        var model = TempPlanModel()
        val nowList = mutableListOf<String>()
        val lacksList = mutableListOf<String>()
        var amount: Int
        var lacks: Int
        val list = mutableListOf<PlanMonthModel>()
        runBlocking {
            launch {
                for (id in planMonthDao.getId()) {
                    amount = 0
                    list.add(planMonthDao.getModelToId(id).toPlanModel())
                    for (item in planDao.getDateList(planMonthDao.getModelToId(id).id)) {
                        amount = amount + item.amount.toInt()
                    }
                    lacks = planMonthDao.getModelToId(id).amount.toInt() - amount
                    lacksList.add(lacks.toString())
                    nowList.add(amount.toString())
                }
                model = TempPlanModel(
                    list, lacksList, nowList
                )
            }
        }
        return model
    }


    override fun createMonthPlan(
        date: String, month: String, amount: String,
        day: String, isMonth: Boolean
    ):
            CheckModelToPlan {
        val isBlankToSource = false
        var isBlankToAmount = false
        var isZero = false
        var isNegative = false
        var isSuccess = false
        var isBlankToDate = false
        val isExpectedToDate = false
        runBlocking {
            launch {
                when {
                    amount.isBlank() -> isBlankToAmount = true
                    month.isBlank() -> isBlankToDate = true
                    date.isBlank() -> isBlankToDate = true
                    !checkNumber(amount.toInt()) -> isNegative = true
                    !checkNumberToZero(amount.toInt()) -> isZero = true
                    amount.subSequence(0, 1) == "0" -> isZero = true
                    else -> {
                        isSuccess = true
                        planMonthDao.createPlanMonth(
                            MonthPlanRoomModel(
                                amount = amount,
                                date = date,
                                month = month,
                                day = day,
                                isMonth = isMonth
                            )
                        )
                    }
                }
            }
        }
        return CheckModelToPlan(
            isSuccess, isBlankToAmount, isBlankToSource, isZero, isNegative, isBlankToDate,
            isExpectedToDate
        )
    }

    override fun getPlanToId(id: Int): List<PlanModel> {
        val model = mutableListOf<PlanModel>()
        val hashList = HashSet<String>()
        runBlocking {
            launch {
                withContext(Dispatchers.IO) {
                    for (lol in planDao.getDayToId(id)) {
                        hashList.add(lol)
                    }
                }
                withContext(Dispatchers.IO) {
                    for (hash in hashList) {
                        val temp = planDao.getListPlanToId(id, hash)
                        if (temp.size - 2 >= 0) {

                            model.add(
                                PlanModel(
                                    month = temp[temp.size - 1].month,
                                    date = hash,
                                    amountFirst = temp[temp.size - 1].amount,
                                    amountSecond = temp[temp.size - 2].amount,
                                    sourceFirst = temp[temp.size - 1].source,
                                    sourceSecond = temp[temp.size - 2].source
                                )
                            )
                        } else {
                            model.add(
                                PlanModel(
                                    month = temp[temp.size - 1].month,
                                    date = hash,
                                    amountFirst = temp[temp.size - 1].amount,
                                    sourceFirst = temp[temp.size - 1].source,
                                )
                            )
                        }
                    }
                }
            }
        }
        return model
    }

    private fun checkDate(id: Int, day: String): Boolean {
        var isTemp = false
        runBlocking {
            launch {
                val model = planMonthDao.getModelToId(id)
                if (day.toInt() > model.day.toInt())
                    isTemp = true
            }
        }
        return isTemp
    }

    private fun MonthPlanRoomModel.toPlanModel(): PlanMonthModel {
        with(this) {
            return PlanMonthModel(
                amount, month, date, id, day, isMonth
            )
        }
    }
}