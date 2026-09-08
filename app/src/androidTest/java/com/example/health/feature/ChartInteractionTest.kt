package com.example.health.feature

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.example.health.feature.chart.StepsBarChart
import org.junit.Rule
import org.junit.Test

class ChartInteractionTest {
    @get:Rule val compose = createComposeRule()
    @Test fun narrowChartSelectsBarAndClearsSelectionInGap() {
        compose.setContent { MaterialTheme { StepsBarChart(listOf(100f, 100f), Modifier.width(180.dp)) } }
        val chart = compose.onNodeWithContentDescription("近7日步数图表")
        chart.performTouchInput { click(Offset(width / 4f, height / 2f)) }
        compose.onNodeWithText("第1天：100步").assertExists()
        chart.performTouchInput { click(Offset(width / 2f, height / 2f)) }
        compose.onNodeWithText("点击柱形查看当天步数").assertExists()
    }
    @Test fun emptyChartRemainsInteractiveWithoutSelection() {
        compose.setContent { MaterialTheme { StepsBarChart(emptyList(), Modifier.width(320.dp)) } }
        compose.onNodeWithContentDescription("近7日步数图表").performClick()
        compose.onNodeWithText("点击柱形查看当天步数").assertExists()
    }
}
