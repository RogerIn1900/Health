package com.example.health.Myhealth.Vitality

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakewharton.threetenabp.AndroidThreeTen
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale



@Composable
fun VitalityDates(navController: NavController){
    CalendarScreen()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreen() {
    // 使用 Box 实现层叠布局
    Box(modifier = Modifier.fillMaxSize()) {
        // 滚动内容
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 160.dp) // 为顶部固定区域预留空间
        ) {
            items(months) { month ->
                MonthCalendar(month = month)
            }
        }

        // 固定顶部区域
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            // 历史数据标题
            Text(
                text = "历史数据",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // 当前日期
            Text(
                text = "2025年4月7日",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // 固定周标题
            WeekDaysHeader()
        }
    }
}

@Composable
fun MonthCalendar(month: YearMonth) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        // 月份标题（保留月份分隔）
        Text(
            text = "${month.monthValue}月",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // 日期网格（已移除周标题）
        val dates = getDatesForMonth(month)
        val weeks = dates.chunked(7)

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            weeks.forEach { week ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    week.forEach { date ->
                        DayCell(date = date, month = month)
                    }

                    if (week.size < 7) {
                        repeat(7 - week.size) {
                            Box(modifier = Modifier.size(40.dp))
                        }
                    }
                }
            }
        }
    }
}

// 其他组件保持不变...
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeekDaysHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DayOfWeek.values().forEach { dayOfWeek ->
            Text(
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.CHINA),
                textAlign = TextAlign.Center,
                modifier = Modifier.size(40.dp),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayCell(date: LocalDate, month: YearMonth) {
    val isCurrentMonth = date.month == month.month
    val textColor = if (isCurrentMonth) MaterialTheme.colorScheme.onBackground else Color.Gray

    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        // 如果是当前日期，可以添加特殊样式
        val isToday = date == LocalDate.now()
        if (isToday) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
            )
        }

        Text(
            text = date.dayOfMonth.toString(),
            color = if (isToday) MaterialTheme.colorScheme.onPrimary else textColor,
            fontSize = 16.sp,
            fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDatesForMonth(month: YearMonth): List<LocalDate> {
    val firstDayOfMonth = month.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek
    val startOffset = (firstDayOfWeek.value - DayOfWeek.MONDAY.value + 7) % 7

    val startDate = firstDayOfMonth.minusDays(startOffset.toLong())
    val endDate = month.atEndOfMonth()
    val endDayOfWeek = endDate.dayOfWeek
    val endOffset = (DayOfWeek.SUNDAY.value - endDayOfWeek.value) % 7

    val dates = mutableListOf<LocalDate>()
    var currentDate = startDate

    while (currentDate <= endDate.plusDays(endOffset.toLong())) {
        dates.add(currentDate)
        currentDate = currentDate.plusDays(1)
    }

    return dates
}

//修改bug
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this) // 初始化 ThreeTenABP
    }
}

@Preview
@Composable
fun CalendarScreenPreview(){
    CalendarScreen()
}