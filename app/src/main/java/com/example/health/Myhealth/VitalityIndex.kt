package com.example.health.Myhealth

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.health.MineCard2
import com.example.health.R
import org.intellij.lang.annotations.PrintFormat
import java.util.Date
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalityIndex(navController:NavController) {
    Column {
        weekGraph()
        vitalityPart1()
        vitalityPart2()
        vitalityPart3()
        HealthBarChartWithInteractionsAndImage()
    }
}



//TODO: 周图表
@Composable
fun weekGraph(){
    // 一周日期图表
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for( i in 1..7){
            VitalityCard(i, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun vitalityPart1(){
    Card {
        graph()
        Spacer(modifier = Modifier.height(28.dp))
        dataPart()
    }
}

data class onePartData(val pic : Int,val name : String,)
@Composable
fun vitalityPart2() {
    Column {

    }
}

@Composable
fun vitalityPartTest2() {
    Card (
    ) {
        Column (
            modifier = Modifier.padding(5.dp)

        ){
            Row {
                Image(
                    painter = painterResource(id = R.mipmap.calorie),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                val name = "卡路里（千卡）"
                Text(name,
                    fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            var check = true
            if(check){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("253", fontSize = 22.sp)
                        Text("当前消耗")
                    }
                    Column (
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text("343", fontSize = 22.sp)
                        Text("上周日均消耗")
                    }
                }
            } else {
                Card {
                    Column {
                        val data1 = 8639
                        val data2 = 6000
                        val date = Date(2025,3,7)
                        Text("${data1}步，目标${data2}步")
                        Text(date.toString())
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.padding(10.dp),
                colors = CardColors(Color.LightGray,Color.Black,Color.White,Color.Black)
            ){
                Text("目标进度已过半，继续加油", modifier = Modifier.padding(5.dp).fillMaxWidth())
            }
        }
    }
}




@Composable
fun vitalityPart3() {
//    TODO("Not yet implemented")
}


//TODO: 当日图表
@Composable
fun graph(){
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "",
        modifier = Modifier.fillMaxWidth()
    )
}
//TODO: 当日数据

data class VitalityData(val pic:Int,val name:String,var data:Int,val afterData:String)

@Composable
fun dataPart(){
    var calorie = 253
    var feet = 253
    var time = 253

    var data = VitalityData (pic = 0,name = "",data = 1,afterData = "")
    var datas = arrayOf(
        VitalityData (pic = R.mipmap.calorie,name = "卡路里（千卡）",data =calorie,afterData = "/400"),
        VitalityData (pic = R.mipmap.shoe,name = "步数（步）",data =feet,afterData = "/400"),
        VitalityData (pic = R.mipmap.clock,name = "中高强度（分）",data = time,afterData = "/400")
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for( data in datas){
            vitalityCard2(data,
                modifier = Modifier.weight(1f)
                    .padding(5.dp)
            )
        }
    }
}

//TODO: 卡路里图表
//TODO: 步数图表
//TODO: 中高强度运动图表
//TODO: 活力成就



@Composable
fun VitalityCard(day: Int,modifier: Modifier) {
    Column(
        modifier = modifier.padding(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(day){
            1 -> Text("一")
            2 -> Text("二")
            3 -> Text("三")
            4 -> Text("四")
            5 -> Text("五")
            6 -> Text("六")
            7 -> Text("日")
        }
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = ""
        )
        Text(day.toString())
    }
}

@Composable
fun vitalityCard2(data: VitalityData,modifier: Modifier = Modifier) {
    Column(
    ) {
        Row {
            Image(
                painter = painterResource(id = data.pic),
                contentDescription = "",
                modifier = Modifier.size(10.dp)
            )
            Text(data.name, fontSize = 10.sp)
        }
        Row {
            Text(data.data.toString(), fontSize = 18.sp)
            Text(data.afterData, fontSize = 18.sp, color = Color.Gray)
        }
    }
}

//图表
@Composable
fun LineChart(data: List<Float>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val maxData = data.maxOrNull() ?: 1f

        for (i in 0 until data.size - 1) {
            val x1 = i * (width / (data.size - 1))
            val y1 = height - (data[i] / maxData) * height
            val x2 = (i + 1) * (width / (data.size - 1))
            val y2 = height - (data[i + 1] / maxData) * height
            drawLine(Color.Blue, Offset(x1, y1), Offset(x2, y2), strokeWidth = 2f)
        }
    }
}
@Composable
fun ChartScreen() {
    val sampleData = listOf(
        10f, 20f, 15f, 30f, 25f, 40f, 35f, 50f, 45f, 60f, 55f, 70f, 65f, 80f, 75f, 90f, 85f, 100f
    )
    LineChart(data = sampleData)
}


@Composable
fun BarChart(data: List<Float>) {
    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .size(200.dp)
        .padding(16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = (canvasWidth / data.size) * 0.2f // 柱子的宽度
        val gapWidth = (canvasWidth / data.size) * 0.8f // 柱子之间的间距

        // 绘制柱子
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
            val left = index * (barWidth + gapWidth) // 柱子的左边界
            val top = canvasHeight - barHeight // 柱子的顶部位置

            // 绘制柱子
            drawRect(
                color = Color.Blue,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight)
            )

            // 绘制柱子的边框
            drawRect(
                color = Color.Black,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight),
                style = Stroke(width = 2f)
            )

        }

    }

}

@Composable
fun BarChartWithLabels(data: List<Float>) {
    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
        val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

        // 绘制柱子
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
            val left = index * (barWidth + gapWidth) // 柱子的左边界
            val top = canvasHeight - barHeight // 柱子的顶部位置

            // 绘制柱子
            drawRect(
                color = Color.Blue,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight)
            )

            // 绘制柱子的边框
            drawRect(
                color = Color.Black,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight),
                style = Stroke(width = 2f)
            )

            // 在柱子顶部绘制数值标签
            val label = value.toInt().toString()
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            )
            val textOffset = Offset(
                x = left + (barWidth - textLayoutResult.size.width) / 2,
                y = top - textLayoutResult.size.height - 4.dp.toPx() // 将标签放在柱子上方
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = textOffset
            )
        }
    }
}


@Composable
fun BarChartWithAxisLabels(data: List<Float>, xLabels: List<String>) {
    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
        val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

        // 绘制 Y 轴
        val yAxisWidth = 2f
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight),
            end = Offset(0f, 0f),
            strokeWidth = yAxisWidth
        )

        // 绘制 Y 轴刻度
        val yAxisSteps = 5 // Y 轴刻度数量
        val yStepHeight = canvasHeight / yAxisSteps
        for (i in 0..yAxisSteps) {
            val y = canvasHeight - i * yStepHeight
            // 绘制刻度线
            drawLine(
                color = Color.Gray,
                start = Offset(0f, y),
                end = Offset(10f, y), // 刻度线长度
                strokeWidth = 1f
            )
            // 绘制刻度值
            val label = "%.1f".format(i * (maxValue / yAxisSteps))
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(-textLayoutResult.size.width - 8.dp.toPx(), y - textLayoutResult.size.height / 2)
            )
        }

        // 绘制 X 轴
        val xAxisHeight = 2f
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight),
            end = Offset(canvasWidth, canvasHeight),
            strokeWidth = xAxisHeight
        )

        // 绘制柱子
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
            val left = index * (barWidth + gapWidth) // 柱子的左边界
            val top = canvasHeight - barHeight // 柱子的顶部位置

            // 绘制柱子
            drawRect(
                color = Color.Blue,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight)
            )

            // 绘制柱子的边框
            drawRect(
                color = Color.Black,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight),
                style = Stroke(width = 2f)
            )

            // 在柱子底部绘制 X 轴标签
            if (index < xLabels.size) {
                val label = xLabels[index]
                val textLayoutResult = textMeasurer.measure(
                    text = label,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                )
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        left + (barWidth - textLayoutResult.size.width) / 2,
                        canvasHeight + 4.dp.toPx() // 将标签放在 X 轴下方
                    )
                )
            }
        }
    }
}


@Composable
fun BarChartWithAxisLabelsAndDashedLine(data: List<Float>, xLabels: List<String>) {
    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
        val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

        // 绘制 Y 轴
        val yAxisWidth = 2f
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight),
            end = Offset(0f, 0f),
            strokeWidth = yAxisWidth
        )

        // 绘制 Y 轴刻度
        val yAxisSteps = 5 // Y 轴刻度数量
        val yStepHeight = canvasHeight / yAxisSteps
        for (i in 0..yAxisSteps) {
            val y = canvasHeight - i * yStepHeight
            // 绘制刻度线
            drawLine(
                color = Color.Gray,
                start = Offset(0f, y),
                end = Offset(10f, y), // 刻度线长度
                strokeWidth = 1f
            )
            // 绘制刻度值
            val label = "%.1f".format(i * (maxValue / yAxisSteps))
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(-textLayoutResult.size.width - 8.dp.toPx(), y - textLayoutResult.size.height / 2)
            )
        }

        // 绘制 X 轴
        val xAxisHeight = 2f
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight),
            end = Offset(canvasWidth, canvasHeight),
            strokeWidth = xAxisHeight
        )

        // 绘制柱子
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
            val left = index * (barWidth + gapWidth) // 柱子的左边界
            val top = canvasHeight - barHeight // 柱子的顶部位置

            // 绘制柱子
            drawRect(
                color = Color.Blue,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight)
            )

            // 绘制柱子的边框
            drawRect(
                color = Color.Black,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight),
                style = Stroke(width = 2f)
            )

            // 在柱子底部绘制 X 轴标签
            if (index < xLabels.size) {
                val label = xLabels[index]
                val textLayoutResult = textMeasurer.measure(
                    text = label,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                )
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        left + (barWidth - textLayoutResult.size.width) / 2,
                        canvasHeight + 4.dp.toPx() // 将标签放在 X 轴下方
                    )
                )
            }
        }

        // 在值为 20 的位置绘制一条虚线
        val targetValue = 20f
        if (targetValue <= maxValue) {
            val y = canvasHeight - (targetValue / maxValue) * canvasHeight
            drawLine(
                color = Color.Red,
                start = Offset(0f, y),
                end = Offset(canvasWidth, y),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
            )

            // 在虚线右侧添加标签
            val label = "Target: $targetValue"
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                    y - textLayoutResult.size.height / 2
                )
            )
        }
    }
}


@Composable
fun BarChartWithRightYAxis(data: List<Float>, xLabels: List<String>) {
    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
        val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

        // 绘制 Y 轴（右侧）
        val yAxisWidth = 2f
        drawLine(
            color = Color.Black,
            start = Offset(canvasWidth, canvasHeight),
            end = Offset(canvasWidth, 0f),
            strokeWidth = yAxisWidth
        )

        // 绘制 Y 轴刻度
        val yAxisSteps = 5 // Y 轴刻度数量
        val yStepHeight = canvasHeight / yAxisSteps
        for (i in 0..yAxisSteps) {
            val y = canvasHeight - i * yStepHeight
            // 绘制刻度线
            drawLine(
                color = Color.Gray,
                start = Offset(canvasWidth, y),
                end = Offset(canvasWidth - 10f, y), // 刻度线长度
                strokeWidth = 1f
            )
            // 绘制刻度值
            val label = "%.1f".format(i * (maxValue / yAxisSteps))
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                    y - textLayoutResult.size.height / 2
                )
            )
        }

        // 绘制 X 轴
        val xAxisHeight = 2f
        drawLine(
            color = Color.Black,
            start = Offset(0f, canvasHeight),
            end = Offset(canvasWidth, canvasHeight),
            strokeWidth = xAxisHeight
        )

        // 绘制柱子
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
            val left = index * (barWidth + gapWidth) // 柱子的左边界
            val top = canvasHeight - barHeight // 柱子的顶部位置

            // 绘制柱子
            drawRect(
                color = Color.Blue,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight)
            )

            // 绘制柱子的边框
            drawRect(
                color = Color.Black,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight),
                style = Stroke(width = 2f)
            )

            // 在柱子底部绘制 X 轴标签
            if (index < xLabels.size) {
                val label = xLabels[index]
                val textLayoutResult = textMeasurer.measure(
                    text = label,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                )
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        left + (barWidth - textLayoutResult.size.width) / 2,
                        canvasHeight + 4.dp.toPx() // 将标签放在 X 轴下方
                    )
                )
            }
        }

        // 在值为 20 的位置绘制一条虚线
        val targetValue = 20f
        if (targetValue <= maxValue) {
            val y = canvasHeight - (targetValue / maxValue) * canvasHeight
            drawLine(
                color = Color.Red,
                start = Offset(0f, y),
                end = Offset(canvasWidth, y),
                strokeWidth = 2f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
            )

            // 在虚线右侧添加标签
            val label = "Target: $targetValue"
            val textLayoutResult = textMeasurer.measure(
                text = label,
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End
                )
            )
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                    y - textLayoutResult.size.height / 2
                )
            )
        }
    }
}


@Composable
fun HealthBarChart() {
    // 数据
    val data = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f) // 对应周一至周日的数据
    val xLabels = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日") // X 轴标签
    val yLabels = listOf("0", "35", "70", "105", "140") // Y 轴刻度值

    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
                val canvasWidth = size.width
                val canvasHeight = size.height
                val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
                val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

                // 绘制 Y 轴
                val yAxisWidth = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, canvasHeight),
                    end = Offset(0f, 0f),
                    strokeWidth = yAxisWidth
                )

                // 绘制 Y 轴刻度
                val yAxisSteps = yLabels.size - 1 // Y 轴刻度数量
                val yStepHeight = canvasHeight / yAxisSteps
                for (i in 0..yAxisSteps) {
                    val y = canvasHeight - i * yStepHeight
                    // 绘制刻度线
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, y),
                        end = Offset(10f, y), // 刻度线长度
                        strokeWidth = 1f
                    )
                    // 绘制刻度值
                    val label = yLabels[i]
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End
                        )
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(-textLayoutResult.size.width - 8.dp.toPx(), y - textLayoutResult.size.height / 2)
                    )
                }

                // 绘制 X 轴
                val xAxisHeight = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, canvasHeight),
                    end = Offset(canvasWidth, canvasHeight),
                    strokeWidth = xAxisHeight
                )

                // 绘制柱子
                data.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
                    val left = index * (barWidth + gapWidth) // 柱子的左边界
                    val top = canvasHeight - barHeight // 柱子的顶部位置

                    // 绘制柱子
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight)
                    )

                    // 绘制柱子的边框
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight),
                        style = Stroke(width = 2f)
                    )

                    // 在柱子底部绘制 X 轴标签
                    if (index < xLabels.size) {
                        val label = xLabels[index]
                        val textLayoutResult = textMeasurer.measure(
                            text = label,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = Offset(
                                left + (barWidth - textLayoutResult.size.width) / 2,
                                canvasHeight + 4.dp.toPx() // 将标签放在 X 轴下方
                            )
                        )
                    }
                }
            }
}

@Composable
fun HealthBarChartWithImage() {
    // 数据
    val data = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f) // 对应周一至周日的数据
    val xLabels = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日") // X 轴标签
    val yLabels = listOf("0", "35", "70", "105", "140") // Y 轴刻度值

    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height
                    val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
                    val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

                    // 绘制 Y 轴（右侧）
                    val yAxisWidth = 2f
                    drawLine(
                        color = Color.Black,
                        start = Offset(canvasWidth, canvasHeight),
                        end = Offset(canvasWidth, 0f),
                        strokeWidth = yAxisWidth
                    )

                    // 绘制 Y 轴刻度
                    val yAxisSteps = yLabels.size - 1 // Y 轴刻度数量
                    val yStepHeight = canvasHeight / yAxisSteps
                    for (i in 0..yAxisSteps) {
                        val y = canvasHeight - i * yStepHeight
                        // 绘制刻度线
                        drawLine(
                            color = Color.Gray,
                            start = Offset(canvasWidth, y),
                            end = Offset(canvasWidth - 10f, y), // 刻度线长度
                            strokeWidth = 1f
                        )
                        // 绘制刻度值
                        val label = yLabels[i]
                        val textLayoutResult = textMeasurer.measure(
                            text = label,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Start
                            )
                        )
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = Offset(
                                canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                                y - textLayoutResult.size.height / 2
                            )
                        )
                    }

                    // 绘制 X 轴
                    val xAxisHeight = 2f
                    drawLine(
                        color = Color.Black,
                        start = Offset(0f, canvasHeight),
                        end = Offset(canvasWidth, canvasHeight),
                        strokeWidth = xAxisHeight
                    )

                    // 绘制柱子
                    data.forEachIndexed { index, value ->
                        val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
                        val left = index * (barWidth + gapWidth) // 柱子的左边界
                        val top = canvasHeight - barHeight // 柱子的顶部位置

                        // 绘制柱子
                        drawRect(
                            color = Color.Blue,
                            topLeft = Offset(left, top),
                            size = Size(barWidth, barHeight)
                        )

                        // 绘制柱子的边框
                        drawRect(
                            color = Color.Black,
                            topLeft = Offset(left, top),
                            size = Size(barWidth, barHeight),
                            style = Stroke(width = 2f)
                        )
                    }

                    // 在值为 35 的位置绘制一条虚线
                    val targetValue = 35f
                    if (targetValue <= maxValue) {
                        val y = canvasHeight - (targetValue / maxValue) * canvasHeight
                        drawLine(
                            color = Color.Red,
                            start = Offset(0f, y),
                            end = Offset(canvasWidth, y),
                            strokeWidth = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
                        )

                        // 在虚线右侧添加标签
                        val label = "Target: $targetValue"
                        val textLayoutResult = textMeasurer.measure(
                            text = label,
                            style = TextStyle(
                                color = Color.Red,
                                fontSize = 12.sp,
                                textAlign = TextAlign.End
                            )
                        )
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = Offset(
                                canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                                y - textLayoutResult.size.height / 2
                            )
                        )
                    }
                }

                // 在每条柱子下方添加标签和图片
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    data.forEachIndexed { index, _ ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // 添加图片（替换为你的图片资源）
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            // 添加标签
                            Text(
                                text = xLabels[index],
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }
}

@Composable
fun HealthBarChartWithLabelsAndImages() {
    // 数据
    val data = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f) // 对应周一至周日的数据
    val xLabels = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日") // X 轴标签
    val yLabels = listOf("0", "35", "70", "105", "140") // Y 轴刻度值

    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // 图表部分
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
                val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

                // 绘制 Y 轴（右侧）
                val yAxisWidth = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(canvasWidth, canvasHeight),
                    end = Offset(canvasWidth, 0f),
                    strokeWidth = yAxisWidth
                )

                // 绘制 Y 轴刻度
                val yAxisSteps = yLabels.size - 1 // Y 轴刻度数量
                val yStepHeight = canvasHeight / yAxisSteps
                for (i in 0..yAxisSteps) {
                    val y = canvasHeight - i * yStepHeight
                    // 绘制刻度线
                    drawLine(
                        color = Color.Gray,
                        start = Offset(canvasWidth, y),
                        end = Offset(canvasWidth - 10f, y), // 刻度线长度
                        strokeWidth = 1f
                    )
                    // 绘制刻度值（放在 Y 轴右侧）
                    val label = yLabels[i]
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(
                            canvasWidth + 8.dp.toPx(), // 将标签放在 Y 轴右侧
                            y - textLayoutResult.size.height / 2
                        )
                    )
                }

                // 绘制 X 轴
                val xAxisHeight = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, canvasHeight),
                    end = Offset(canvasWidth, canvasHeight),
                    strokeWidth = xAxisHeight
                )

                // 绘制柱子
                data.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
                    val left = index * (barWidth + gapWidth) // 柱子的左边界
                    val top = canvasHeight - barHeight // 柱子的顶部位置

                    // 绘制柱子
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight)
                    )

                    // 绘制柱子的边框
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight),
                        style = Stroke(width = 2f)
                    )
                }

                // 在值为 35 的位置绘制一条虚线
                val targetValue = 35f
                if (targetValue <= maxValue) {
                    val y = canvasHeight - (targetValue / maxValue) * canvasHeight
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
                    )

                    // 在虚线右侧添加标签
                    val label = "Target: $targetValue"
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End
                        )
                    )
//                    drawText(
//                        textLayoutResult = textLayoutResult,
//                        topLeft = Offset(
//                            canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
//                            y - textLayoutResult.size.height / 2
//                        )
//                    )
                }
            }
        }

        // X 轴标签和图片部分
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            data.forEachIndexed { index, _ ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 添加标签
                    Text(
                        text = xLabels[index],
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    // 添加图片（替换为你的图片资源）
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun HealthBarChartWithInteractions() {
    // 数据
    val data = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f) // 对应周一至周日的数据
    val xLabels = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日") // X 轴标签
    val yLabels = listOf("0", "35", "70", "105", "140") // Y 轴刻度值

    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    // 状态：记录被点击的柱子索引
    var clickedBarIndex by remember { mutableStateOf<Int?>(null) }

    // 状态：记录是否点击了值为 35 的虚线
    var isTargetLineClicked by remember { mutableStateOf(false) }
    var targetLineClickPosition by remember { mutableStateOf(Offset.Zero) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 图表部分
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            // 计算点击的柱子索引
                            val barWidth = (size.width / data.size) * 0.8f
                            val gapWidth = (size.width / data.size) * 0.2f
                            val index = (offset.x / (barWidth + gapWidth)).toInt()
                            if (index in data.indices) {
                                clickedBarIndex = index
                            }

                            // 检查是否点击了值为 35 的虚线
                            val targetY = size.height - (35f / maxValue) * size.height
                            if (offset.y in (targetY - 10f)..(targetY + 10f)) {
                                isTargetLineClicked = true
                                targetLineClickPosition = offset
                            } else {
                                isTargetLineClicked = false
                            }
                        }
                    }
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
                val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

                // 绘制 Y 轴（右侧）
                val yAxisWidth = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(canvasWidth, canvasHeight),
                    end = Offset(canvasWidth, 0f),
                    strokeWidth = yAxisWidth
                )

                // 绘制 Y 轴刻度
                val yAxisSteps = yLabels.size - 1 // Y 轴刻度数量
                val yStepHeight = canvasHeight / yAxisSteps
                for (i in 0..yAxisSteps) {
                    val y = canvasHeight - i * yStepHeight
                    // 绘制刻度线
                    drawLine(
                        color = Color.Gray,
                        start = Offset(canvasWidth, y),
                        end = Offset(canvasWidth - 10f, y), // 刻度线长度
                        strokeWidth = 1f
                    )
                    // 绘制刻度值（放在 Y 轴右侧）
                    val label = yLabels[i]
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(
                            canvasWidth + 8.dp.toPx(), // 将标签放在 Y 轴右侧
                            y - textLayoutResult.size.height / 2
                        )
                    )
                }

                // 绘制 X 轴
                val xAxisHeight = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, canvasHeight),
                    end = Offset(canvasWidth, canvasHeight),
                    strokeWidth = xAxisHeight
                )

                // 绘制柱子
                data.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
                    val left = index * (barWidth + gapWidth) // 柱子的左边界
                    val top = canvasHeight - barHeight // 柱子的顶部位置

                    // 绘制柱子
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight)
                    )

                    // 绘制柱子的边框
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight),
                        style = Stroke(width = 2f)
                    )
                }

                // 在值为 35 的位置绘制一条虚线
                val targetValue = 35f
                if (targetValue <= maxValue) {
                    val y = canvasHeight - (targetValue / maxValue) * canvasHeight
                    drawLine(
                        color = Color.Red,
                        start = Offset(0f, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
                    )

                    // 在虚线右侧添加标签
                    val label = "Target: $targetValue"
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End
                        )
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(
                            canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                            y - textLayoutResult.size.height / 2
                        )
                    )
                }

                // 在被点击的柱子中间绘制一条虚线
                clickedBarIndex?.let { index ->
                    val left = index * (barWidth + gapWidth)
                    val centerX = left + barWidth / 2
                    drawLine(
                        color = Color.Green,
                        start = Offset(centerX, 0f),
                        end = Offset(centerX, canvasHeight),
                        strokeWidth = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
                    )
                }

                // 在点击值为 35 的虚线位置上方绘制一个小图形
                if (isTargetLineClicked) {
                    drawCircle(
                        color = Color.Magenta,
                        radius = 10f,
                        center = Offset(targetLineClickPosition.x, targetLineClickPosition.y - 20f)
                    )
                }
            }
        }

        // X 轴标签和图片部分
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            data.forEachIndexed { index, _ ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 添加图片（替换为你的图片资源）
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    // 添加标签
                    Text(
                        text = xLabels[index],
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun HealthBarChartWithInteractionsAndImage() {
    // 数据
    val data = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f) // 对应周一至周日的数据
    val xLabels = listOf("周一", "周二", "周三", "周四", "周五", "周六", "周日") // X 轴标签
    val yLabels = listOf("0", "35", "70", "105", "140") // Y 轴刻度值

    // 获取数据中的最大值，用于计算柱子的高度
    val maxValue = data.maxOrNull() ?: 1f

    // 用于测量文本大小
    val textMeasurer = rememberTextMeasurer()

    // 状态：记录被点击的柱子索引
    var clickedBarIndex by remember { mutableStateOf<Int?>(null) }

    // 状态：记录是否点击了值为 35 的虚线
    var isTargetLineClicked by remember { mutableStateOf(false) }
    var targetLineClickPosition by remember { mutableStateOf(Offset.Zero) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 图表上方显示的图片（当柱子被点击时显示）
        if (clickedBarIndex != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                    contentDescription = "Clicked Bar Image",
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        // 图表部分
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            // 计算点击的柱子索引
                            val barWidth = (size.width / data.size) * 0.8f
                            val gapWidth = (size.width / data.size) * 0.2f
                            val index = (offset.x / (barWidth + gapWidth)).toInt()
                            if (index in data.indices) {
                                clickedBarIndex = index
                            }

                            // 检查是否点击了值为 35 的虚线
                            val targetY = size.height - (35f / maxValue) * size.height
                            if (offset.y in (targetY - 10f)..(targetY + 10f)) {
                                isTargetLineClicked = true
                                targetLineClickPosition = offset
                            } else {
                                isTargetLineClicked = false
                            }
                        }
                    }
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                val barWidth = (canvasWidth / data.size) * 0.8f // 柱子的宽度
                val gapWidth = (canvasWidth / data.size) * 0.2f // 柱子之间的间距

                // 绘制 Y 轴（右侧）
                val yAxisWidth = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(canvasWidth, canvasHeight),
                    end = Offset(canvasWidth, 0f),
                    strokeWidth = yAxisWidth
                )

                // 绘制 Y 轴刻度
                val yAxisSteps = yLabels.size - 1 // Y 轴刻度数量
                val yStepHeight = canvasHeight / yAxisSteps
                for (i in 0..yAxisSteps) {
                    val y = canvasHeight - i * yStepHeight
                    // 绘制刻度线
                    drawLine(
                        color = Color.Gray,
                        start = Offset(canvasWidth, y),
                        end = Offset(canvasWidth - 10f, y), // 刻度线长度
                        strokeWidth = 1f
                    )
                    // 绘制刻度值（放在 Y 轴右侧）
                    val label = yLabels[i]
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start
                        )
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(
                            canvasWidth + 8.dp.toPx(), // 将标签放在 Y 轴右侧
                            y - textLayoutResult.size.height / 2
                        )
                    )
                }

                // 绘制 X 轴
                val xAxisHeight = 2f
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, canvasHeight),
                    end = Offset(canvasWidth, canvasHeight),
                    strokeWidth = xAxisHeight
                )

                // 绘制柱子
                data.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
                    val left = index * (barWidth + gapWidth) // 柱子的左边界
                    val top = canvasHeight - barHeight // 柱子的顶部位置

                    // 绘制柱子
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight)
                    )

                    // 绘制柱子的边框
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(left, top),
                        size = Size(barWidth, barHeight),
                        style = Stroke(width = 2f)
                    )
                }

                // 在值为 35 的位置绘制一条虚线
                val targetValue = 35f
                if (targetValue <= maxValue) {
                    val y = canvasHeight - (targetValue / maxValue) * canvasHeight
                    drawLine(
                        color = Color.Red,
                        start = Offset(0f, y),
                        end = Offset(canvasWidth, y),
                        strokeWidth = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
                    )

                    // 在虚线右侧添加标签
                    val label = "Target: $targetValue"
                    val textLayoutResult = textMeasurer.measure(
                        text = label,
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = 12.sp,
                            textAlign = TextAlign.End
                        )
                    )
                    drawText(
                        textLayoutResult = textLayoutResult,
                        topLeft = Offset(
                            canvasWidth - textLayoutResult.size.width - 8.dp.toPx(),
                            y - textLayoutResult.size.height / 2
                        )
                    )
                }

                // 在被点击的柱子中间绘制一条虚线
                clickedBarIndex?.let { index ->
                    val left = index * (barWidth + gapWidth)
                    val centerX = left + barWidth / 2
                    drawLine(
                        color = Color.Green,
                        start = Offset(centerX, 0f),
                        end = Offset(centerX, canvasHeight),
                        strokeWidth = 2f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 虚线效果
                    )
                }

                // 在点击值为 35 的虚线位置上方绘制一个小图形
                if (isTargetLineClicked) {
                    drawCircle(
                        color = Color.Magenta,
                        radius = 10f,
                        center = Offset(targetLineClickPosition.x, targetLineClickPosition.y - 20f)
                    )
                }
            }
        }

        // X 轴标签和图片部分
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            data.forEachIndexed { index, _ ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 添加图片（替换为你的图片资源）
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    // 添加标签
                    Text(
                        text = xLabels[index],
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun BarChartScreen() {
    val sampleData = listOf(
        10f, 20f, 15f, 30f, 25f, 40f, 35f
    )

//    BarChart(data = sampleData)
    BarChartWithLabels(data = sampleData)

}

@Composable
fun BarChartScreen2() {
    val sampleData = listOf(
        10f, 20f, 15f, 30f, 25f, 40f, 35f
    )
    val xLabels = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    BarChartWithRightYAxis(data = sampleData, xLabels = xLabels)
}

@Preview
@Composable
fun previewVitalityPartTest2() {
//    vitalityPartTest2()
//    BarChartScreen()
//    BarChartScreen2()
//    HealthBarChart()
    HealthBarChartWithInteractionsAndImage()
}