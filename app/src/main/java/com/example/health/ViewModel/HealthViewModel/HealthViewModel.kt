package com.example.health.ViewModel.HealthViewModel

import android.app.Application
import androidx.datastore.core.FileStorage
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.Room.VitalityDatas.DailyHealthData
import com.example.health.Room.VitalityDatas.HealthDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HealthViewModel(application: Application) : AndroidViewModel(application) {
    private val database = HealthDatabase.getDatabase(application)
    private val repository = HealthRepository(database.healthDao())
//    private val fileStorage = FileStorage(application)

    val allData: Flow<List<DailyHealthData>> = repository.allData

    fun getDailyData(date: String) = repository.getDailyData(date)

    fun updateCalories(date: String, value: Double) = viewModelScope.launch {
        repository.updateCalories(date, value)
    }

    fun updateSteps(date: String, value: Int) = viewModelScope.launch {
        repository.updateSteps(date, value)
    }

    fun updateMvpa(date: String, minutes: Int) = viewModelScope.launch {
        repository.updateMvpa(date, minutes)
    }

    fun saveFullData(data: DailyHealthData) = viewModelScope.launch {
        repository.insertOrUpdate(data)
    }


//    //测试
//    // 生成模拟数据
//    fun generateMockData() = fileStorage.generateMockData()
//
//    // 保存数据到文件
//    suspend fun saveDataToFile(data: List<DailyHealthData>) {
//        fileStorage.saveToFile(data)
//    }
//
//    // 从文件加载数据
//    suspend fun loadDataFromFile() = fileStorage.loadFromFile()
//
//    // 批量插入数据库
//    suspend fun batchInsert(data: List<DailyHealthData>) {
//        data.forEach { repository.insertOrUpdate(it) }
//    }
}