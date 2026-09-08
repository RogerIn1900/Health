package com.example.health.feature.records

import org.junit.Assert.*
import org.junit.Test

class HealthRecordTest {
    @Test fun `zero values are valid`() { assertEquals(0, HealthRecord("local", 1L, 0, 0, 0).steps) }
    @Test(expected = IllegalArgumentException::class) fun `negative measurements are rejected`() { HealthRecord("local", 1L, 0, -1, 0) }
    @Test(expected = IllegalArgumentException::class) fun `blank identity is rejected`() { HealthRecord(" ", 1L, 0, 0, 0) }
}
