package com.example.health.feature.chart

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun MetricRing(label: String, value: Int, target: Int, modifier: Modifier = Modifier) {
    val sweep by animateFloatAsState(ringSweep(value.toFloat(), target.toFloat()), label = label)
    val foreground = MaterialTheme.colorScheme.primary
    val track = MaterialTheme.colorScheme.surfaceVariant
    Column(modifier.semantics(mergeDescendants = true) { contentDescription = "$label $value / $target" }) {
        Canvas(Modifier.fillMaxWidth().aspectRatio(2f)) {
            val stroke = 10.dp.toPx().coerceAtMost(size.width / 8f)
            val bounds = Size((size.width - stroke).coerceAtLeast(0f), (size.height * 2 - stroke).coerceAtLeast(0f))
            val origin = Offset(stroke / 2, stroke / 2)
            drawArc(track, 180f, HALF_RING_DEGREES, false, origin, bounds, style = Stroke(stroke, cap = StrokeCap.Round))
            drawArc(foreground, 180f, sweep, false, origin, bounds, style = Stroke(stroke, cap = StrokeCap.Round))
        }
        Text(label, style = MaterialTheme.typography.labelLarge)
        Text("$value / $target", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun StepsBarChart(values: List<Float>, modifier: Modifier = Modifier) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var selected by remember(values) { mutableStateOf<Int?>(null) }
    val bars = remember(values, size) { barBounds(values, size.width.toFloat(), size.height.toFloat()) }
    val color = MaterialTheme.colorScheme.primary
    val selectedColor = MaterialTheme.colorScheme.tertiary
    Column(modifier) {
        Text("近7日步数", style = MaterialTheme.typography.titleMedium)
        Canvas(Modifier.fillMaxWidth().height(150.dp)
            .semantics { contentDescription = "近7日步数图表" }
            .onSizeChanged { size = it }
            .pointerInput(bars) { detectTapGestures { selected = hitBar(bars, it.x, it.y) } }
        ) {
            bars.forEachIndexed { index, bar ->
                drawRect(if (index == selected) selectedColor else color,
                    Offset(bar.left, bar.top), Size(bar.right - bar.left, bar.bottom - bar.top))
            }
        }
        Text(selected?.let { "第${it + 1}天：${values[it].toInt()}步" } ?: "点击柱形查看当天步数")
    }
}
