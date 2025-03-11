package com.example.health.Myhealth

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import com.example.health.R
import java.util.Date
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.CloseSegment
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign



@Composable
fun VitalityIndex(navController:NavController) {
    LazyColumn {
        item(){
            weekGraph()
            vitalityPart1()
            vitalityPart2()
            vitalityPart3()
            Spacer(modifier = Modifier.height(10.dp))
            vitalityCard3()

        }
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

@Composable
fun vitalityCard3(){
    val pic = R.mipmap.calorie
    val name = "卡路里（千卡）"
    val a = true
    val content = when(a) {
        true  -> "再燃烧384千卡，才能完成今日卡路里目标"
        false -> ""
    }

    Card(
        modifier = Modifier
            .height(400.dp)
            .width(500.dp)
    ) {

        Column(
            modifier = Modifier.padding(10.dp)
                .height(400.dp)
                .width(500.dp)

        ) {
//                        Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = pic),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text(name)
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.mipmap.arrow_light),
                    contentDescription = "",
                    modifier = Modifier.size(14.dp)
                )
            }

            HealthBarChartWithInteractionsAndImage()

//            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.padding(10.dp),
                colors = CardColors(Color.White, Color.White, Color.White, Color.White)
            ) {
                Text(
                    content,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

//单元图表
@Composable
fun HealthBarChartWithInteractionsAndImage2() {
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
            .height(400.dp)
    ) {
        // 图表上方显示的图片（当柱子被点击时显示）
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                if (clickedBarIndex != null) {

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
                .height(200.dp)
                .fillMaxSize()
        ) {

            //-----------------------------------------
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
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
                val barWidth = (canvasWidth / data.size) * 0.2f // 柱子的宽度
                val gapWidth = (canvasWidth / data.size) * 0.95f // 柱子之间的间距
                val yAxisOffset = 20f

                // 绘制图表外围的灰色边框
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(-50f, 0f),
                    size = Size(canvasWidth + 110f, canvasHeight),
                    style = Stroke(width = 2f) // 边框宽度
                )

                // 绘制 Y 轴（右侧）
//                val yAxisWidth = 2f
//                drawLine(
//                    color = Color.Gray,
//                    start = Offset(canvasWidth + yAxisOffset, canvasHeight),
//                    end = Offset(canvasWidth + yAxisOffset, 0f),
//                    strokeWidth = yAxisWidth
//                )

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
//                drawLine(
//                    color = Color.Black,
//                    start = Offset(0f, canvasHeight),
//                    end = Offset(canvasWidth, canvasHeight),
//                    strokeWidth = xAxisHeight
//                )

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
                            color = Color.Red,
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
//            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .height(300.dp)
            .width(510.dp)
    ) {
        // 图表上方显示的图片（当柱子被点击时显示）
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
//                .background(Color.White)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            if (clickedBarIndex != null) {

                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                    contentDescription = "Clicked Bar Image",
                    modifier = Modifier.size(40.dp)
                )
            } else {
                Row (
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxSize()
                ){
                    Column(
                    horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text("340")
                        Text("当前消耗")
                    }
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Text("340")
                        Text("当前消耗")
                    }
                }
            }
        }

        // 图表部分
        Box(
            modifier = Modifier
                .weight(1f)
//                .fillMaxSize()
                .height(300.dp)
                .width(510.dp)
                .padding(10.dp) // 添加 10.dp 的 padding
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            // 计算点击的柱子索引
                            val barWidth = (size.width / data.size) * 0.2f
                            val gapWidth = (size.width / data.size) * 0.8f
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
                val barWidth = (canvasWidth / data.size) * 0.2f // 柱子的宽度
                val gapWidth = (canvasWidth / data.size) * 0.8f // 柱子之间的间距
                val yAxisOffset = 20f

                // 绘制图表外围的灰色边框
                drawRect(
                    color = Color.Gray,
                    topLeft = Offset(0f, 0f),
                    size = Size(canvasWidth, canvasHeight),
                    style = Stroke(width = 2f) // 边框宽度
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

                // 绘制柱子
                data.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * canvasHeight // 柱子的高度
                    val left = index * (barWidth + gapWidth) + gapWidth / 2 // 柱子的左边界
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
                }

                // 在被点击的柱子中间绘制一条虚线
                clickedBarIndex?.let { index ->
                    val left = index * (barWidth + gapWidth) + gapWidth / 2
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
                .width(490.dp)
//                .padding(top = 8.dp),
                .padding(start = 10.dp, end = 10.dp),

            horizontalArrangement = Arrangement.SpaceAround
        ) {
            data.forEachIndexed { index, _ ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

//                    modifier = Modifier.padding(start = 15.dp, end = 15.dp)
                ) {

                    // 添加标签
                    Text(
                        text = xLabels[index],
                        style = TextStyle(
                            color = Color.Black,
//                            fontSize = 10.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    // 添加图片（替换为你的图片资源）
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // 替换为你的图片资源
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewVitalityPartTest2() {
    vitalityCard3()
}