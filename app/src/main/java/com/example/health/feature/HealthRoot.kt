package com.example.health.feature

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.health.feature.chart.MetricRing
import com.example.health.feature.chart.StepsBarChart
import com.example.health.feature.records.*
import com.example.health.feature.scan.*
import java.time.LocalDate

// Demonstration goals for manually entered records, expressed in kcal, steps and minutes.
private const val CALORIE_GOAL_KCAL = 500
private const val STEP_GOAL = 10_000
private const val ACTIVE_GOAL_MINUTES = 30

@Composable
fun HealthRoot() {
    val application = LocalContext.current.applicationContext as HealthApplication
    val model: HealthViewModel = viewModel(factory = remember(application) {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                HealthViewModel(application.records, LocalDate.now().toEpochDay()) as T
        }
    })
    MaterialTheme(colorScheme = lightColorScheme()) {
        Surface(Modifier.fillMaxSize()) { HealthScreen(model) }
    }
}

@Composable
fun HealthScreen(model: HealthViewModel) {
    val records by model.records.collectAsState()
    val day by model.day.collectAsState()
    val message by model.message.collectAsState()
    val busy by model.busy.collectAsState()
    val record = records.firstOrNull { it.epochDay == day }
    var calories by rememberSaveable(day) { mutableStateOf("") }
    var steps by rememberSaveable(day) { mutableStateOf("") }
    var minutes by rememberSaveable(day) { mutableStateOf("") }
    LaunchedEffect(day, record) {
        calories = record?.calories?.toString() ?: "0"
        steps = record?.steps?.toString() ?: "0"
        minutes = record?.activeMinutes?.toString() ?: "0"
    }
    var scanning by rememberSaveable { mutableStateOf(false) }
    var result by remember { mutableStateOf<ScanResult?>(null) }
    val context = LocalContext.current
    BackHandler(scanning) { scanning = false }
    Column(Modifier.fillMaxSize().safeDrawingPadding().verticalScroll(rememberScrollState()).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Health · 健康记录", style = MaterialTheme.typography.headlineSmall)
        Text("本地档案 · 手动记录", style = MaterialTheme.typography.labelLarge)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(onClick = { model.selectDay(day - 1) }, enabled = !busy) { Text("前一天") }
            Text(LocalDate.ofEpochDay(day).toString())
            TextButton(onClick = { model.selectDay(day + 1) }, enabled = !busy) { Text("后一天") }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricRing("热量 kcal", record?.calories ?: 0, CALORIE_GOAL_KCAL, Modifier.weight(1f))
            MetricRing("步数", record?.steps ?: 0, STEP_GOAL, Modifier.weight(1f))
            MetricRing("活动分钟", record?.activeMinutes ?: 0, ACTIVE_GOAL_MINUTES, Modifier.weight(1f))
        }
        OutlinedTextField(calories, { calories = it }, label = { Text("热量 kcal") }, modifier = Modifier.fillMaxWidth().testTag("calories"), singleLine = true)
        OutlinedTextField(steps, { steps = it }, label = { Text("步数") }, modifier = Modifier.fillMaxWidth().testTag("steps"), singleLine = true)
        OutlinedTextField(minutes, { minutes = it }, label = { Text("活动分钟") }, modifier = Modifier.fillMaxWidth().testTag("minutes"), singleLine = true)
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { model.save(calories, steps, minutes) }, enabled = !busy, modifier = Modifier.testTag("save")) { Text("保存记录") }
            OutlinedButton(onClick = model::delete, enabled = !busy && record != null) { Text("删除当天") }
        }
        message?.let { Text(it, modifier = Modifier.testTag("message")) }
        val week = (day - HISTORY_DAYS + 1..day).map { date -> records.firstOrNull { it.epochDay == date }?.steps?.toFloat() ?: 0f }
        StepsBarChart(week)
        OutlinedButton(onClick = { scanning = !scanning }) { Text(if (scanning) "关闭扫描" else "扫描二维码") }
        if (scanning) ScannerScreen { result = it; scanning = false }
        result?.let { scanned ->
            Text("扫描结果：${scanned.text}")
            if (scanned is ScanResult.Link) {
                TextButton(onClick = {
                    try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(scanned.text))) }
                    catch (_: ActivityNotFoundException) { result = ScanResult.Text("未找到可打开该链接的应用") }
                }) { Text("打开链接") }
            }
        }
    }
}
