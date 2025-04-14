package com.example.health.ViewModel.HealthViewModel


import com.example.health.Room.VitalityDatas.DailyHealthData
import com.example.health.Room.VitalityDatas.HealthDao
import kotlinx.coroutines.flow.Flow

class HealthRepository(private val healthDao: HealthDao) {
    val allData: Flow<List<DailyHealthData>> = healthDao.getAllData()

    suspend fun insertOrUpdate(data: DailyHealthData) {
        healthDao.insertOrUpdate(data)
    }

    fun getDailyData(date: String) = healthDao.getByDate(date)

    suspend fun updateCalories(date: String, value: Double) {
        healthDao.updateCalories(date, value)
    }

    suspend fun updateSteps(date: String, value: Int) {
        healthDao.updateSteps(date, value)
    }

    suspend fun updateMvpa(date: String, minutes: Int) {
        healthDao.updateMvpa(date, minutes)
    }
}