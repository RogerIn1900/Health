package com.example.health.Room.VitalityDatas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(data: DailyHealthData)

    @Query("SELECT * FROM daily_health WHERE date = :date")
    fun getByDate(date: String): Flow<DailyHealthData?>

    @Query("SELECT * FROM daily_health ORDER BY date DESC")
    fun getAllData(): Flow<List<DailyHealthData>>

    @Query("UPDATE daily_health SET calories = :calories WHERE date = :date")
    suspend fun updateCalories(date: String, calories: Double)

    @Query("UPDATE daily_health SET steps = :steps WHERE date = :date")
    suspend fun updateSteps(date: String, steps: Int)

    @Query("UPDATE daily_health SET mvpaMinutes = :minutes WHERE date = :date")
    suspend fun updateMvpa(date: String, minutes: Int)
}