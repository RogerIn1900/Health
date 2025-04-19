package com.example.health.db.repository

import com.example.health.db.VitalityDatabase.VitalityDatabase
import com.example.health.db.entity.VitalityEntity
import java.util.Date

class VitalityRepository(private val db: VitalityDatabase) {
    //获取活力指标记录
    fun getVitalityData():MutableList<VitalityEntity>
    {
        return db.VitalityStateDao().getAllDatas()
    }

    //新增活力指标记录
    suspend fun insertVitalityState(vitalityState: VitalityEntity)
    {
        val oldDatas = db
            .VitalityStateDao()
            .getAllDatas()
        if(oldDatas != null){
            db.VitalityStateDao().update(vitalityState)
        }else{
            db.VitalityStateDao().insertData(vitalityState)
        }
    }

    //更新活力指标记录
    suspend fun updateVitalityData(vitalityState: VitalityEntity){
        db.VitalityStateDao().update(vitalityState)
    }

    //通过日期删除活力指标数据
    suspend fun deleteByDate(year:Int,month:Int,date:Int){
        val vitalityEntity = db.VitalityStateDao().getByDate(year = year, month = month,date = date)
        if(vitalityEntity != null){
            vitalityEntity.isDeleted = "1"
            db.VitalityStateDao().update(vitalityEntity)
        }
    }

    //清除所用活力指标记录
    suspend fun clearAllHistory(){
        db.VitalityStateDao().clearAll()
    }
}