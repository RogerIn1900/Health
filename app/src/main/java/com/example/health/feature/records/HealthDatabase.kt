package com.example.health.feature.records

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(record: HealthRecord)

    @Query("SELECT * FROM health_records WHERE userId = :userId AND epochDay BETWEEN :startDay AND :endDay AND deleted = 0 ORDER BY epochDay")
    fun observeRange(userId: String, startDay: Long, endDay: Long): Flow<List<HealthRecord>>

    @Query("UPDATE health_records SET deleted = 1 WHERE userId = :userId AND epochDay = :epochDay")
    suspend fun delete(userId: String, epochDay: Long)
}

@Database(entities = [HealthRecord::class], version = 1, exportSchema = true)
abstract class HealthDatabase : RoomDatabase() {
    abstract fun records(): HealthRecordDao
    companion object {
        private const val DATABASE_NAME = "health-records.db"
        fun open(context: Context): HealthDatabase = Room.databaseBuilder(
            context.applicationContext, HealthDatabase::class.java, DATABASE_NAME
        ).build()
    }
}

class HealthRepository(private val dao: HealthRecordDao) {
    fun observeRange(userId: String, startDay: Long, endDay: Long): Flow<List<HealthRecord>> {
        require(userId.isNotBlank() && startDay <= endDay)
        return dao.observeRange(userId, startDay, endDay)
    }
    // Saving a deleted date deliberately restores it. Other users and dates are untouched.
    suspend fun save(record: HealthRecord) = dao.save(record.copy(deleted = false))
    suspend fun delete(userId: String, epochDay: Long) {
        require(userId.isNotBlank())
        dao.delete(userId, epochDay)
    }
}
