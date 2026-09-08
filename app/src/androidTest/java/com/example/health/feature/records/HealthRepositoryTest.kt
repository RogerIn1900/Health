package com.example.health.feature.records

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HealthRepositoryTest {
    private lateinit var db: HealthDatabase
    private lateinit var repo: HealthRepository
    @Before fun setUp() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), HealthDatabase::class.java).build()
        repo = HealthRepository(db.records())
    }
    @After fun tearDown() = db.close()
    private fun record(user: String = "a", day: Long = 10L, steps: Int = 100) = HealthRecord(user, day, 50, steps, 20)

    @Test fun sameDateUpdatesWithoutDuplicating() = runBlocking {
        repo.save(record()); repo.save(record(steps = 200))
        val rows = repo.observeRange("a", 10, 10).first()
        assertEquals(1, rows.size); assertEquals(200, rows.single().steps)
    }
    @Test fun differentUsersAndDatesRemainIsolated() = runBlocking {
        repo.save(record()); repo.save(record("b")); repo.save(record(day = 11L))
        assertEquals(2, repo.observeRange("a", 10, 11).first().size)
        assertEquals(1, repo.observeRange("b", 10, 11).first().size)
    }
    @Test fun softDeleteHidesOnlySelectedDateAndSaveRestoresIt() = runBlocking {
        repo.save(record()); repo.save(record(day = 11L))
        repo.delete("a", 10L)
        assertEquals(listOf(11L), repo.observeRange("a", 10, 11).first().map { it.epochDay })
        repo.save(record())
        assertEquals(2, repo.observeRange("a", 10, 11).first().size)
    }
    @Test fun emptyRangeIsReadableWithoutNetwork() = runBlocking {
        assertTrue(repo.observeRange("a", 0, 5).first().isEmpty())
    }
}
