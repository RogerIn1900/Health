package com.example.health.feature.records

import androidx.room.Entity

@Entity(tableName = "health_records", primaryKeys = ["userId", "epochDay"])
data class HealthRecord(
    val userId: String,
    val epochDay: Long,
    val calories: Int,
    val steps: Int,
    val activeMinutes: Int,
    val deleted: Boolean = false
) {
    init {
        require(userId.isNotBlank()) { "用户标识不能为空" }
        require(calories >= 0 && steps >= 0 && activeMinutes >= 0) { "健康记录不能为负数" }
    }
}
