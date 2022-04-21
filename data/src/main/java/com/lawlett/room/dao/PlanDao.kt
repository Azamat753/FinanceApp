package com.lawlett.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lawlett.room.model.PlanRoomModel

@Dao
interface PlanDao {

    @Insert
    suspend fun createPlan(planRoomModel: PlanRoomModel)

    @Query("SELECT * FROM planModel WHERE idMonth  =:id ")
    suspend fun getDateList(id: Int): List<PlanRoomModel>

    @Query("SELECT * FROM planModel WHERE idMonth =:id AND date =:date ")
    suspend fun getListPlanToId(id: Int, date: String): List<PlanRoomModel>

    @Query("SELECT date FROM planModel WHERE idMonth =:id")
    suspend fun getDayToId(id: Int): List<String>

}