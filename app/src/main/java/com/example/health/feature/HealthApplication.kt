package com.example.health.feature

import android.app.Application
import com.example.health.feature.records.HealthDatabase
import com.example.health.feature.records.HealthRepository

class HealthApplication : Application() {
    private val database by lazy { HealthDatabase.open(this) }
    val records by lazy { HealthRepository(database.records()) }
}
