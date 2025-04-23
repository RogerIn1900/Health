package com.example.health.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.health.db.entity.VitalityEntity
import org.threeten.bp.Month
import org.threeten.bp.Year
import java.util.Date

@Dao
interface VitalityDao {
    //查询全部数据
    @Query("select * from Vitality_data order by id desc")
    fun getAllDatas():MutableList<VitalityEntity>

    //查询指定日期的数据
    @Transaction
    @Query("select * from Vitality_data where year = :year and month = :month and date = :date")
    suspend fun getByDate(year: Int,month: Int,date: Int):VitalityEntity

    //修改数据
    @Update
    suspend fun update(vitalityState: VitalityEntity)

    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(vitalityState: VitalityEntity)

    //删除
    @Query("delete from Vitality_data")
    suspend fun clearAll()
}