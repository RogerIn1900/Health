package com.example.health.Myhealth.Vitality

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.R
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

    val year = LocalDate.now().year
    val month = LocalDate.now().month.value
    val date = LocalDate.now().dayOfMonth
    // 示例数据 - 可以替换为实际数据源
    val months = listOf(
        YearMonth.of(2025, 3),  // 2025年3月
        YearMonth.of(2025, 4),  // 2025年4月
        YearMonth.of(2025, 5)   // 2025年5月
    )

    Column {
        Text(
            text = "历史数据",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = ""+year+"年"+month+"月"+date+"日",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        WeekDaysHeader()

        LazyColumn {
            items(months) { month ->
                MonthCalendar(month = month)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthCalendar(month: YearMonth) {
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        // 月份标题
        Text(
            text = "${month.monthValue}月",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // 星期标题行
//        WeekDaysHeader()

        // 日期网格
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

                    // 如果一周不足7天，补充空单元格
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
            .size(55.dp),
        contentAlignment = Alignment.Center
    ) {
        // 如果是当前日期，可以添加特殊样式
        val isToday = date == LocalDate.now()


        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally // 水平居中
        ){
            if(isCurrentMonth){
                myGraph(modifier = Modifier.size(40.dp))
                Text(
                    text = date.dayOfMonth.toString(),
                    color = if (isToday) MaterialTheme.colorScheme.onPrimary else textColor,
                    fontSize = 10.sp,
                    fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                    textAlign = TextAlign.Center,//要和外部的居中一起用才能实现
                    modifier = Modifier.fillMaxWidth()
                )
                if (isToday) {
//                Image(
//                    painter = painterResource(id = R.mipmap.calorie),
//                    contentDescription = "",
//                    modifier = Modifier.fillMaxWidth().height(2.dp)
//                )
                }
            }
        }

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