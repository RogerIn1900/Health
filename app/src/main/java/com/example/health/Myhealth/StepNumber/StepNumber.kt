package com.example.health.Myhealth.StepNumber

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.health.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessTrackingScreen() {
    val lightBlue = Color(0xFF58CCED)
    val blue = Color(0xFF3895D3)
    val orange = Color(0xFFFF8F00)
    val yellow = Color(0xFFFFD700)
    val backgroundGray = Color(0xFFF0F2F5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("活力月历", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle forward navigation */ }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Forward")
                    }
                    IconButton(onClick = { /* Handle menu */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(backgroundGray)
        ) {
            // Date Selector
            DateSelector()

            // Daily Progress Indicators
            WeeklyProgressIndicators()

            // Main Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Semi-circular Progress Gauge
                    ProgressGauge(progress = 0.63f) // 253/400 ≈ 0.63

                    Spacer(modifier = Modifier.height(24.dp))

                    // Stats Row
                    StatsRow()

                    Spacer(modifier = Modifier.height(24.dp))

                    // Weekly Bar Chart
                    WeeklyBarChart()

                    Spacer(modifier = Modifier.height(16.dp))

                    // Remaining Calories Text
                    Text(
                        text = "再燃烧384千卡，才能完成今日卡路里目标",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DateSelector() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("2025年3月5日", fontWeight = FontWeight.Bold)
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Expand",
                tint = Color.Black
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val days = listOf("一", "二", "三", "四", "五", "六", "日")
            days.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun WeeklyProgressIndicators() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 1..7) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RainbowIcon(isComplete = i < 6)
                Text(
                    text = "$i",
                    modifier = Modifier.padding(top = 4.dp),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun RainbowIcon(isComplete: Boolean) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .padding(4.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = size.width * 0.15f
            val radius = (size.width - strokeWidth) / 2
            val centerX = size.width / 2
            val centerY = size.height / 2

            // Draw rainbow arc
            val colors = listOf(
                Color(0xFFFF4500), // Red-Orange
                Color(0xFFFFD700), // Yellow
                Color(0xFF58CCED)  // Light Blue
            )

            for (i in 0..2) {
                val startAngle = 180f
                val sweepAngle = 180f
                val padding = i * strokeWidth

                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(padding, padding),
                    size = Size(size.width - (padding * 2), size.height - (padding * 2)),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }
        }
    }
}

@Composable
fun ProgressGauge(progress: Float) {
    Box(
        modifier = Modifier
            .size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = size.width * 0.1f
            val radius = (size.width - strokeWidth) / 2
            val centerX = size.width / 2
            val centerY = size.height

            // Background arcs
            val trackColors = listOf(
                Color(0xFFE0E0E0), // Gray for track
                Color(0xFFE0E0E0),
                Color(0xFFE0E0E0)
            )

            // Progress arc colors
            val progressColors = listOf(
                Color(0xFFFF4500), // Red-Orange
                Color(0xFFFFD700), // Yellow
                Color(0xFF58CCED)  // Light Blue
            )

            for (i in 0..2) {
                val startAngle = 180f
                val sweepAngle = 180f
                val padding = i * strokeWidth

                // Draw track (background)
                drawArc(
                    color = trackColors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = Offset(padding, padding),
                    size = Size(size.width - (padding * 2), size.height - (padding * 2)),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                // Draw progress
                drawArc(
                    color = progressColors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle * progress,
                    useCenter = false,
                    topLeft = Offset(padding, padding),
                    size = Size(size.width - (padding * 2), size.height - (padding * 2)),
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )

                // Draw little arrows on progress
                if (i <= 2) {
                    val angle = (startAngle + (sweepAngle * progress)) * (PI / 180f)
                    val arrowX = centerX + cos(angle).toFloat() * (radius - (i * strokeWidth) - (strokeWidth / 2))
                    val arrowY = centerY - sin(angle).toFloat() * (radius - (i * strokeWidth) - (strokeWidth / 2))

                    drawCircle(
                        color = Color.White,
                        radius = strokeWidth * 0.3f,
                        center = Offset(arrowX, arrowY)
                    )
                }
            }
        }
    }
}

@Composable
fun StatsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem(
            emoji = "\uD83D\uDD25", // Fire emoji
            label = "卡路里 (千卡)",
            value = "253",
            total = "400",
            color = Color(0xFFFF6F00)
        )

        StatItem(
            emoji = "\uD83D\uDEB6", // Walking person emoji
            label = "步数 (步)",
            value = "253",
            total = "400",
            color = Color(0xFFFFD700)
        )

        StatItem(
            emoji = "\uD83D\uDC99", // Blue heart emoji (closest to the blue in image)
            label = "中高强度 (分)",
            value = "253",
            total = "400",
            color = Color(0xFF58CCED)
        )
    }
}

@Composable
fun StatItem(emoji: String, label: String, value: String, total: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = emoji, fontSize = 16.sp)
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Text(
            text = "$value/$total",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WeeklyBarChart() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFFFF4500), CircleShape)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "🔥",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "卡路里 (千卡)",
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Details",
                tint = Color.Gray
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "340",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = "340",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "当前消耗",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Text(
                text = "当前消耗",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bar Chart
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            val values = listOf(140, 105, 70, 35, 5, 50, 90)
            val days = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日")

            for (i in values.indices) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height((values[i] / 140f * 120).dp)
                            .background(Color(0xFFFF4500))
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = days[i],
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessTrackingScreenPreview() {
    MaterialTheme {
        FitnessTrackingScreen()
    }
}

@Composable
fun StepNumber() {
    FitnessTrackingScreen()
}