package com.example.health.db.repository

import com.example.health.db.VitalityDatabase.VitalityDatabase
import com.example.health.db.entity.VitalityState
import java.util.Date

class VitalityRepository(private val db: VitalityDatabase) {
    //获取活力指标记录
    fun getVitalityData():MutableList<VitalityState>
    {
        return db.VitalityStateDao().getAllDatas()
    }

    //新增活力指标记录
    suspend fun insertVitalityState(vitalityState: VitalityState)
    {
        val oldDatas = db
            .VitalityStateDao()
            .getDataByDate(vitalityState.date)

        if(oldDatas != null){
            db.VitalityStateDao().update(vitalityState)
        }else{
            db.VitalityStateDao().insertData(vitalityState)
        }
    }

    //更新活力指标记录
    suspend fun updateVitalityData(vitalityState: VitalityState){
        db.VitalityStateDao().update(vitalityState)
    }

    //通过日期删除活力指标数据
    suspend fun deleteByDate(date: Date){
        val vitalityState = db.VitalityStateDao().getDataByDate(date)
        if(vitalityState != null){
            vitalityState.isDelete = "1"
            db.VitalityStateDao().update(vitalityState)
        }
    }

    //清除所用活力指标记录
    suspend fun clearAllHistory(){
        db.VitalityStateDao().clearAll()
    }
}