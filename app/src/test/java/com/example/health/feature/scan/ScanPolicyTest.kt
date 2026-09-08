package com.example.health.feature.scan

import org.junit.Assert.*
import org.junit.Test

class ScanPolicyTest {
    @Test fun `padded and interleaved Y plane copies only luminance`() {
        assertArrayEquals(byteArrayOf(1, 2, 3, 4), copyLuminance(byteArrayOf(1, 0, 2, 9, 3, 0, 4), 2, 2, 4, 2))
    }
    @Test(expected = IllegalArgumentException::class) fun `truncated plane is rejected`() {
        copyLuminance(byteArrayOf(1), 2, 2, 2, 1)
    }
    @Test fun `clockwise rotation handles rectangular frames`() {
        val rotated = rotateLuminance(byteArrayOf(1, 2, 3, 4, 5, 6), 3, 2, 90)
        assertEquals(2, rotated.width); assertEquals(3, rotated.height)
        assertArrayEquals(byteArrayOf(4, 1, 5, 2, 6, 3), rotated.bytes)
        val restored = rotateLuminance(rotated.bytes, 2, 3, 270)
        assertArrayEquals(byteArrayOf(1, 2, 3, 4, 5, 6), restored.bytes)
    }
    @Test fun `half rotation reverses pixels`() {
        assertArrayEquals(byteArrayOf(4, 3, 2, 1), rotateLuminance(byteArrayOf(1, 2, 3, 4), 2, 2, 180).bytes)
    }
    @Test fun `scan result only treats explicit web URLs as links`() {
        assertTrue(classifyScan("https://example.test/a") is ScanResult.Link)
        assertTrue(classifyScan("普通文本") is ScanResult.Text)
        assertTrue(classifyScan("intent://open") is ScanResult.Text)
        assertTrue(classifyScan("https:") is ScanResult.Text)
    }
    @Test fun `duplicate frames are suppressed until interval expires`() {
        var now = 0L
        val gate = ScanGate { now }
        assertTrue(gate.accept("code")); assertFalse(gate.accept("code"))
        now = 1_500L
        assertTrue(gate.accept("code")); assertTrue(gate.accept("different"))
        assertFalse(gate.accept(" "))
    }
}
