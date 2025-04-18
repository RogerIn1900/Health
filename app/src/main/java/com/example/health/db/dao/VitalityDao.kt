package com.example.health.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.health.db.entity.VitalityState
import java.util.Date

@Dao
interface VitalityDao {
    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(vitalityState: VitalityState)

    //查询指定日期的数据
//    @Transaction
    @Query("select * from Vitality_data where date = :date")
    suspend fun getDataByDate(date:Date):VitalityState?

    //查询全部数据
    @Query("select * from Vitality_data where isDeleted = '0' order by date desc")
    fun getAllDatas():MutableList<VitalityState>

    //修改数据
    @Update
    suspend fun update(vitalityState: VitalityState)


    //删除
    @Query("delete from Vitality_data")
    suspend fun clearAll()


}