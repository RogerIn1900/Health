package com.example.health

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.LinearProgressIndicator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.drawscope.DrawScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalfPieChartProgressBar(progress: Float = 0.75f) {
    // 确保进度值在 0 到 1 之间
    val adjustedProgress = progress.coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("半扇形进度条示例", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // 绘制扇形进度条
        Canvas(modifier = Modifier.size(100.dp)) {
            drawHalfPieChart(adjustedProgress)
        }
    }
}

private fun DrawScope.drawHalfPieChart(progress: Float) {
    val sweepAngle = 180f * progress // 将满进度设置为180度
    val radius = size.minDimension / 2 // 圆的半径

    // 绘制半扇形
    drawArc(
        color = Color.Blue,
        startAngle = -90f, // 从顶部开始
        sweepAngle = sweepAngle,
        useCenter = false,
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 8.dp.toPx()),
        topLeft = androidx.compose.ui.geometry.Offset((size.width - radius * 2) / 2, (size.height - radius * 2) / 2),
        size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2)
    )

    // 绘制圆形背景
    drawCircle(
        color = Color.Gray.copy(alpha = 0.3f),
        radius = radius,
        center = center
    )
}
@Composable
fun IndeterminateCircularIndicator() {
    var loading by remember { mutableStateOf(false) }

    Button(onClick = { loading = true }, enabled = !loading) {
        Text("Start loading")
    }

    if (!loading) return

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun LinearDeterminateIndicator() {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() // Create a coroutine scope

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            scope.launch {
                loadProgress { progress ->
                    currentProgress = progress
                }
                loading = false // Reset loading when the coroutine finishes
            }
        }, enabled = !loading) {
            Text("Start loading")
        }

        if (loading) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

/** Iterate the progress value */
suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Composable
fun LinearProgressExample() {
    Column {
        LinearProgressIndicator() // 无限滚动的进度条
        Spacer(modifier = Modifier.height(20.dp))
        LinearProgressIndicator(progress = 0.6f, color = Color.Blue ) // 确定进度的进度条
    }
}

@Composable
fun CircularProgressExample() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator() // 无限旋转的进度条
        Spacer(modifier = Modifier.height(40.dp)
            .width(40.dp))
        CircularProgressIndicator(
            modifier = Modifier.size(40.dp),
            progress = 0.5f,
            color = Color.Blue,
            strokeWidth = 12.dp
        ) // 确定进度的进度条
    }
}


@Preview
@Composable
fun PreviewCircularProgressExample() {
    Column {
        HalfPieChartProgressBar()
        IndeterminateCircularIndicator()
        LinearDeterminateIndicator()
        LinearProgressExample()
        CircularProgressExample()
    }
}