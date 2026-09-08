package com.example.health.feature.scan

import java.net.URI

sealed class ScanResult(open val text: String) {
    data class Link(override val text: String) : ScanResult(text)
    data class Text(override val text: String) : ScanResult(text)
}

fun classifyScan(value: String): ScanResult {
    val text = value.trim()
    val uri = runCatching { URI(text) }.getOrNull()
    return if (uri?.scheme?.lowercase() in setOf("https", "http") && !uri?.host.isNullOrBlank()) ScanResult.Link(text)
    else ScanResult.Text(text)
}

// Suppress repeated frames of the same code for 1.5 seconds; a different code is accepted immediately.
private const val DUPLICATE_INTERVAL_MS = 1_500L
class ScanGate(private val nowMs: () -> Long) {
    private var previous: String? = null
    private var previousAtMs = 0L
    fun accept(value: String): Boolean {
        if (value.isBlank()) return false
        val now = nowMs()
        if (value == previous && now - previousAtMs < DUPLICATE_INTERVAL_MS) return false
        previous = value
        previousAtMs = now
        return true
    }
}
