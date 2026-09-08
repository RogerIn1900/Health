package com.example.health.feature.chart

import org.junit.Assert.*
import org.junit.Test

class ChartGeometryTest {
    @Test fun `half goal maps to quarter circle`() { assertEquals(90f, ringSweep(50f, 100f), 0f) }
    @Test fun `over goal fills half ring without wrapping`() { assertEquals(180f, ringSweep(150f, 100f), 0f) }
    @Test fun `invalid values never produce invalid geometry`() {
        for (value in listOf(-1f, Float.NaN, Float.POSITIVE_INFINITY)) assertEquals(0f, ringSweep(value, 100f), 0f)
        for (target in listOf(0f, -1f, Float.NaN)) assertEquals(0f, ringSweep(50f, target), 0f)
    }
    @Test fun `empty or zero sized chart has no bars`() {
        assertTrue(barBounds(emptyList(), 100f, 100f).isEmpty())
        assertTrue(barBounds(listOf(1f), 0f, 100f).isEmpty())
    }
    @Test fun `zero and negative bars are finite and not selectable`() {
        val bars = barBounds(listOf(0f, -10f), 100f, 100f)
        assertTrue(bars.all { it.top == 100f })
        assertNull(hitBar(bars, 25f, 100f))
    }
    @Test fun `hit testing rejects gaps and space above bar`() {
        val bars = barBounds(listOf(1f, 2f), 100f, 100f)
        assertEquals(0, hitBar(bars, 25f, 75f))
        assertNull(hitBar(bars, 50f, 75f))
        assertNull(hitBar(bars, 25f, 25f))
        assertNull(hitBar(bars, -1f, 50f))
    }
    @Test fun `geometry scales with available size`() {
        val small = barBounds(listOf(1f, 2f), 100f, 80f)
        val large = barBounds(listOf(1f, 2f), 200f, 160f)
        assertEquals(small[0].left * 2, large[0].left, 0f)
        assertEquals(small[0].top * 2, large[0].top, 0f)
    }
}
