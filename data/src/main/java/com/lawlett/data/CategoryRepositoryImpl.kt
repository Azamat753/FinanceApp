package com.lawlett.data

import com.lawlett.domain.model.BalanceModel
import com.lawlett.domain.repo.CategoryRepository
import com.lawlett.room.dao.CategoryDao
import com.lawlett.ext.SUM_COST
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor
    (
    private val categoryDao: CategoryDao
) :
    CategoryRepository {
    override fun getAllCategory(): List<BalanceModel> {
        val balanceList = ArrayList<BalanceModel>()
        val originalList: MutableList<BalanceModel> = mutableListOf()
        val list = HashSet<String>()
        var cost: Int
        var costToString: String
        runBlocking {
            launch {
                if (categoryDao.getListCategory().isNotEmpty()) {
                    for (item in categoryDao.getListCategory()) {
                        list.add(item)
                    }
                }
                for (item in list) {
                    cost = 0
                    costToString = ""
                    val temp = categoryDao.getListToCategory(item)
                    for (ob in temp) {
                        cost = cost + ob.cost.toInt()
                    }
                    costToString = if (cost == 0) {
                        cost.toString()
                    } else {
                        "-$cost"
                    }
                    balanceList.add(
                        BalanceModel(
                            cost = costToString,
                            iconName = item
                        )
                    )
                }
                for (item in balanceList) {
                    if (item.cost != "0") {
                        originalList.add(item)
                    }
                }
            }
            if (getModel().cost != null)
                if (getModel().cost != "0")
                    originalList.add(getModel())
        }
        return originalList
    }

    private fun getModel(): BalanceModel {
        var model = BalanceModel()
        var cost = 0
        var costToString: String
        runBlocking {
            launch {
                val list = categoryDao.getAllList()
                if (list.isNotEmpty()) {
                    for (item in list) {
                        cost = cost + item.cost.toInt()
                    }
                    costToString = if (cost == 0) {
                        cost.toString()
                    } else {
                        "-$cost"
                    }
                    model = BalanceModel(
                        cost = costToString,
                        iconName = SUM_COST
                    )
                }
            }
        }
        return model
    }
}
