package com.example.health.Room.VitalityDatas

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "daily_health")
data class DailyHealthData(
    @PrimaryKey
    val date: String, // 使用 "yyyy-MM-dd" 格式的字符串作为主键
    val calories: Double,       // 卡路里（单位：千卡）
    val steps: Int,             // 步数
    val mvpaMinutes: Int        // 中高强度运动时间（单位：分钟）
) {
    constructor() : this("", 0.0, 0, 0) // 空构造函数供 Room 使用
}