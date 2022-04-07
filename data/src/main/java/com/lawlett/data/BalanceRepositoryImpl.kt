package com.lawlett.data

import android.util.Log
import com.example.core.Constants
import com.example.core.R
import com.lawlett.domain.BalanceRepository
import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.model.CategoryIconModel
import com.lawlett.room.dao.BalanceDao
import com.lawlett.room.model.BalanceRoomModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.math.cos


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
        var balance = 0
            balanceDao.getIncome().collect {
                if (it.isNotEmpty()) {
                    for (item in it) {
                        income = +item.income.toInt()
                        cost = +item.cost.toInt()
                    }
                    balance = income - cost
                    model = BalanceModel(
                        income = income.toString(),
                        cost = cost.toString(),
                        balance = balance.toString()
                    )
                }
            }
        return if (model.balance != null) {
            Log.e("ololo", "getIncome: $model" )
            model
        } else {
            BalanceModel(balance = Constants.EMPTY)
        }
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

    private fun BalanceModel.toRoomModel(): BalanceRoomModel {
        with(this) {
            return BalanceRoomModel(
                balance = balance!!,
                cost = cost!!,
                income = income!!,
                date = date!!,
                icon = icon!!,
                iconName = iconName!!,
                isIncome = isIncome!!
            )
        }
    }

    private fun BalanceRoomModel.toModel(): BalanceModel {
        with(this) {
            return BalanceModel(balance, cost, income, date, icon, iconName, isIncome)
        }
    }
}