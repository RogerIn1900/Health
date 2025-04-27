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
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.health.ui.theme.RingOrange
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun BingoRing(
    itemData :Int = 60,
    target : Float = 50.toFloat(),
    ringColor: Color = RingOrange,
    ){
    val sweepAngle = ((itemData/target) * 360).toFloat()

    if(sweepAngle<360 && sweepAngle>=0) {
        Canvas(
            modifier = Modifier
//                .fillMaxSize()
                .size(36.dp)
        ) {
            val canvasSize = size
            val canvasWidth = canvasSize.width
            val canvasHeight = canvasSize.height

            val ringWidth = (size.width*0.13f)
            val maxRadius = (canvasWidth / 2) - ringWidth/2
            val radius = maxRadius - ringWidth * 1.2f

//        val radius = maxRadius - index * ringWidth * 1.2f // 调整半径，确保圆环不重叠且不超出边界
//        val sweepAngle = (datas[index] * 180).toFloat()

            val paintLightGreen = Paint().apply{
                color = ringColor
                strokeWidth = ringWidth
                isAntiAlias = true
                style = PaintingStyle.Stroke
            }

            val paint = Paint().apply{
                color = ringColor
                strokeWidth = ringWidth
                isAntiAlias = true
                style = PaintingStyle.Stroke
            }

            drawIntoCanvas { canvas ->
                withTransform({
                    translate(canvasWidth / 2, canvasHeight / 2) // 将坐标系移动到 Canvas 的中心
                }) {
                    // 如果 sweepAngle 大于 180f，只绘制 180f 的部分
//                        val drawAngle = if (sweepAngle > 180f) 180f else sweepAngle
                    canvas.nativeCanvas.drawArc(
                        -radius, // 左边界
                        -radius, // 上边界
                        radius,  // 右边界
                        radius,  // 下边界
                        -90f,   // 起始角度
                        sweepAngle,   // 扫过的角度
                        false,  // 不使用中心点连接
                        paint.asFrameworkPaint(), // 使用 Paint
                    )
                }
            }
        }
    }else{
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(ringColor)
                .size(36.dp)
        ){
            Image(
                painter = painterResource(R.drawable.baseline_done_24),
                contentDescription = "backgroud",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FitnessTrackingScreenPreview() {
    MaterialTheme {
        BingoRing()
    }
}

@Composable
fun StepNumber() {
    FitnessAppNavigation()
}


@Composable
fun FitnessAppNavigation() {
    val navController = rememberNavController()
    val currentDate = remember { mutableStateOf(LocalDate.of(2025, 3, 5)) }

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            FitnessTrackingScreen(
                navController = navController,
                currentDate = currentDate.value,
                onDateSelected = { date ->
                    currentDate.value = date
                    navController.navigate("dayDetail/${date}")
                }
            )
        }
        composable("dayDetail/{date}") { backStackEntry ->
            val dateStr = backStackEntry.arguments?.getString("date") ?: return@composable
            val selectedDate = LocalDate.parse(dateStr)
            DayDetailScreen(
                date = selectedDate,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessTrackingScreen(
    navController: NavController,
    currentDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
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
            DateSelector(currentDate)

            // Daily Progress Indicators - now with click handling
            WeeklyProgressIndicators(
                currentDate = currentDate,
                onDateSelected = onDateSelected
            )

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
fun DateSelector(currentDate: LocalDate) {
    val formatter = DateTimeFormatter.ofPattern("yyyy年M月d日")

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
            Text(currentDate.format(formatter), fontWeight = FontWeight.Bold)
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
fun WeeklyProgressIndicators(
    currentDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    // Get the dates for the current week (starting from Monday)
    val startOfWeek = currentDate.minusDays(currentDate.dayOfWeek.value.toLong() - 1)
    val datesOfWeek = (0..6).map { startOfWeek.plusDays(it.toLong()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 0..6) {
            val date = datesOfWeek[i]
            val isCurrentDate = date == currentDate

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onDateSelected(date) }
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                RainbowIcon(isComplete = i < 5)
                Text(
                    text = "${date.dayOfMonth}",
                    modifier = Modifier.padding(top = 4.dp),
                    fontSize = 14.sp,
                    fontWeight = if (isCurrentDate) FontWeight.Bold else FontWeight.Normal,
                    color = if (isCurrentDate) Color.Blue else Color.Black
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayDetailScreen(date: LocalDate, navController: NavController) {
    val formatter = DateTimeFormatter.ofPattern("yyyy年M月d日")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(date.format(formatter)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // This is a placeholder for the day detail screen
            // You would populate this with actual data for the selected date
            Text(
                text = "${date.format(formatter)} 的详细数据",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sample stats for this day
            StatsRow()

            Spacer(modifier = Modifier.height(24.dp))

            // Progress gauge for this specific day
            ProgressGauge(progress = 0.63f)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "此处将显示 ${date.dayOfMonth} 日的详细健康数据和统计信息",
                textAlign = TextAlign.Center
            )
        }
    }
}
