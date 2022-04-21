package com.lawlett.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lawlett.room.model.MonthPlanRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanMonthDao {

    @Insert
    suspend fun createPlanMonth(planRoomModel: MonthPlanRoomModel)

    @Query("SELECT  * FROM monthModel WHERE id =:id")
    suspend fun getModelToId(id: Int): MonthPlanRoomModel


    @Query("SELECT  id FROM monthModel ")
    suspend fun getId(): List<Int>

}