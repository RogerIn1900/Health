package com.example.health.feature.chart

/** A half ring spans 180 degrees. Over-target values fill it, while labels retain actual values. */
const val HALF_RING_DEGREES = 180f
private const val BAR_WIDTH_FRACTION = 0.6f

data class BarBounds(val left: Float, val top: Float, val right: Float, val bottom: Float) {
    fun contains(x: Float, y: Float): Boolean = x >= left && x <= right && y >= top && y <= bottom
}

fun progressFraction(value: Float, target: Float): Float {
    if (!value.isFinite() || !target.isFinite() || value <= 0f || target <= 0f) return 0f
    return (value / target).coerceIn(0f, 1f)
}

fun ringSweep(value: Float, target: Float): Float = progressFraction(value, target) * HALF_RING_DEGREES

fun barBounds(values: List<Float>, widthPx: Float, heightPx: Float): List<BarBounds> {
    if (values.isEmpty() || !widthPx.isFinite() || !heightPx.isFinite() || widthPx <= 0f || heightPx <= 0f) return emptyList()
    val clean = values.map { if (it.isFinite()) it.coerceAtLeast(0f) else 0f }
    val scale = clean.maxOrNull()?.takeIf { it > 0f } ?: 1f
    val slot = widthPx / clean.size
    val barWidth = slot * BAR_WIDTH_FRACTION
    return clean.mapIndexed { index, value ->
        val left = index * slot + (slot - barWidth) / 2f
        BarBounds(left, heightPx * (1f - value / scale), left + barWidth, heightPx)
    }
}

fun hitBar(bars: List<BarBounds>, x: Float, y: Float): Int? =
    bars.indexOfFirst { it.top < it.bottom && it.contains(x, y) }.takeIf { it >= 0 }
