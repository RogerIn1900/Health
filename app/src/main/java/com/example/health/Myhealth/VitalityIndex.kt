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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeCap.Companion.Square
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.health.R
import java.util.Date
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun VitalityIndex(navController:NavController) {
    LazyColumn {
        item(){
            weekGraph()
            vitalityPart1()
            vitalityPart2()
            vitalityPart3()
            Spacer(modifier = Modifier.height(10.dp))
            vitalityCard3(
                pic = R.mipmap.calorie,
                name = "卡路里（千卡）",
                data  =  listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f),
                mycolor = Color.Red
            )
            Spacer(modifier = Modifier.height(10.dp))

            vitalityCard3(
                pic = R.mipmap.shoe,
                name = "卡路里（千卡）",
                data  =  listOf(120f, 15f, 50f, 125f, 30f, 50f, 90f),
                mycolor = Color.Yellow
            )
            Spacer(modifier = Modifier.height(10.dp))

            vitalityCard3(
                pic = R.mipmap.clock,
                name = "卡路里（千卡）",
                data  =  listOf(33f,44f,55f,66f,77f,88f,99f),
                mycolor = Color.Blue
            )
//            fun vitalityCard3(
//                pic :Int = R.mipmap.calorie,
//                name :String = "卡路里（千卡）",
//                a : Boolean = true,
//                data:List<Float> =  listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f)
//            ){
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
fun vitalityPart1() {
    Card(
        modifier = Modifier
            .fillMaxWidth() // 让 Card 填满父容器的宽度
            .wrapContentHeight() // 让 Card 高度根据内容自适应
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // 让 Column 填满 Card 的宽度
                .padding(16.dp), // 添加内边距
            verticalArrangement = Arrangement.Center, // 垂直居中
            horizontalAlignment = Alignment.CenterHorizontally // 水平居中
        ) {
            myGraph() // 图表部分
            Spacer(modifier = Modifier.height(28.dp)) // 间距
            dataPart() // 数据部分
        }
    }
}

data class onePartData(val pic: Int, val name: String)
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
                Text("目标进度已过半，继续加油", modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth())
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
fun graph() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(150.dp) // 设置 Column 的大小为 300dp
            .width(300.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize() // Canvas 填满 Column 的空间
        ) {
            val canvasSize = size // Canvas 的尺寸
            val canvasWidth = canvasSize.width
            val canvasHeight = canvasSize.height

            val ringWidth = 40.dp.toPx() // 半环宽度（转换为像素）
            val maxRadius = (canvasWidth / 2) - ringWidth / 2 // 最大半径，确保圆环不超出 Canvas

            // 定义颜色和画笔
            val paints = listOf(
                Paint().apply {
                    color = Color(0xFFFF5722) // 棕色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
                    strokeCap = Round

                },
                Paint().apply {
                    color = Color(0xFFFFC107) // 另一种棕色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
                    strokeCap = Round

                },
                Paint().apply {
                    color = Color(0xFF2196F3) // 深蓝色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
                    strokeCap = Round

                }
            )
            val datas = listOf(
                191.0 / 400,
                4135.0 / 6000,
                35.0 / 30
            )

            // 绘制三个半圆环
            paints.forEachIndexed { index, paint ->
                val radius = maxRadius - index * ringWidth * 1.2f // 调整半径，确保圆环不重叠且不超出边界
                val sweepAngle = (datas[index] * 180).toFloat()

                // 绘制底层部分
                drawIntoCanvas { canvas ->
                    withTransform({
                        translate(canvasWidth / 2, canvasHeight) // 将坐标系移动到 Canvas 的中心
                    }) {
                        // 如果 sweepAngle 大于 180f，只绘制 180f 的部分
                        val drawAngle = if (sweepAngle > 180f) 180f else sweepAngle
                        canvas.nativeCanvas.drawArc(
                            -radius, // 左边界
                            -radius, // 上边界
                            radius,  // 右边界
                            radius,  // 下边界
                            180f,   // 起始角度
                            drawAngle,   // 扫过的角度
                            false,  // 不使用中心点连接
                            paint.asFrameworkPaint(), // 使用 Paint
                        )
                    }
                }

                // 如果 sweepAngle 大于 180f，绘制上层部分
                if (sweepAngle > 180f) {
                    val lightPaint = Paint().apply {
                        color =lightenColor(paint.color)// 使用原始颜色的浅色,传入的参数不能直接使用color，会变黑
                        strokeWidth = ringWidth
                        isAntiAlias = true
                        style = PaintingStyle.Stroke
                        strokeCap = Round
                    }

                    drawIntoCanvas { canvas ->
                        withTransform({
                            translate(canvasWidth / 2, canvasHeight) // 将坐标系移动到 Canvas 的中心
                        }) {
                            // 绘制超过 180f 的部分
                            canvas.nativeCanvas.drawArc(
                                -radius, // 左边界
                                -radius, // 上边界
                                radius,  // 右边界
                                radius,  // 下边界
                                180f,   // 起始角度
                                sweepAngle - 180f, // 扫过的角度（超过 180f 的部分）
                                false,  // 不使用中心点连接
                                lightPaint.asFrameworkPaint() // 使用浅色 Paint
                            )
                        }
                    }
                }
            }
        }

        // 将颜色变浅的函数

    }
}

//状态提升
@Composable
fun myGraph(calories:Int = 691,steps:Int = 10135,duration:Int = 55) {
    val datas = listOf(
        calories / 400.0,
        steps / 6000.0,
        duration / 30.0
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(150.dp) // 设置 Column 的大小为 300dp
            .width(300.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize() // Canvas 填满 Column 的空间
        ) {
            val canvasSize = size // Canvas 的尺寸
            val canvasWidth = canvasSize.width
            val canvasHeight = canvasSize.height

            val ringWidth = 40.dp.toPx() // 半环宽度（转换为像素）
            val maxRadius = (canvasWidth / 2) - ringWidth / 2 // 最大半径，确保圆环不超出 Canvas

            // 定义颜色和画笔
            val transparentPaints = listOf(
                Paint().apply {
                    color = Color(0x4DFF5722) // 棕色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
//                    strokeCap = Round

                },
                Paint().apply {
                    color = Color(0x4DFFC107) // 另一种棕色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
//                    strokeCap = Round

                },
                Paint().apply {
                    color = Color(0x4D2196F3) // 深蓝色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
//                    strokeCap = Round

                }
            )


            val paints = listOf(
                Paint().apply {
                    color = Color(0xFFFF5722) // 棕色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
//                    strokeCap = Round

                },
                Paint().apply {
                    color = Color(0xFFFFC107) // 另一种棕色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
//                    strokeCap = Round

                },
                Paint().apply {
                    color = Color(0xFF2196F3) // 深蓝色
                    strokeWidth = ringWidth
                    isAntiAlias = true
                    style = PaintingStyle.Stroke
//                    strokeCap = Round

                }
            )

            //绘制三个半圆环的背景
            transparentPaints.forEachIndexed { index, paint ->
                val radius = maxRadius - index * ringWidth * 1.2f // 调整半径，确保圆环不重叠且不超出边界
                val sweepAngle = (datas[index] * 180).toFloat()

                // 绘制底层部分
                drawIntoCanvas { canvas ->
                    withTransform({
                        translate(canvasWidth / 2, canvasHeight) // 将坐标系移动到 Canvas 的中心
                    }) {
                        // 如果 sweepAngle 大于 180f，只绘制 180f 的部分
//                        val drawAngle = if (sweepAngle > 180f) 180f else sweepAngle
                        canvas.nativeCanvas.drawArc(
                            -radius, // 左边界
                            -radius, // 上边界
                            radius,  // 右边界
                            radius,  // 下边界
                            180f,   // 起始角度
                            180f,   // 扫过的角度
                            false,  // 不使用中心点连接
                            paint.asFrameworkPaint(), // 使用 Paint
                        )
                    }
                }
            }

            // 绘制三个半圆环的真实数值
            paints.forEachIndexed { index, paint ->
                val radius = maxRadius - index * ringWidth * 1.2f // 调整半径，确保圆环不重叠且不超出边界
                val sweepAngle = ((datas[index] % 180)* 180).toFloat()

                // 绘制底层部分
                drawIntoCanvas { canvas ->
                    withTransform({
                        translate(canvasWidth / 2, canvasHeight) // 将坐标系移动到 Canvas 的中心
                    }) {
                        // 如果 sweepAngle 大于 180f，只绘制 180f 的部分
                        val drawAngle = if (sweepAngle > 180f) 180f else sweepAngle
                        canvas.nativeCanvas.drawArc(
                            -radius, // 左边界
                            -radius, // 上边界
                            radius,  // 右边界
                            radius,  // 下边界
                            180f,   // 起始角度
                            drawAngle,   // 扫过的角度
                            false,  // 不使用中心点连接
                            paint.asFrameworkPaint(), // 使用 Paint
                        )
                    }
                }

                // 如果 sweepAngle 大于 180f，绘制上层部分
                if (sweepAngle > 180f) {
                    val lightPaint = Paint().apply {
                        color =lightenColor(paint.color)// 使用原始颜色的浅色,传入的参数不能直接使用color，会变黑
                        strokeWidth = ringWidth
                        isAntiAlias = true
                        style = PaintingStyle.Stroke
//                        strokeCap = Round
                    }

                    drawIntoCanvas { canvas ->
                        withTransform({
                            translate(canvasWidth / 2, canvasHeight) // 将坐标系移动到 Canvas 的中心
                        }) {
                            // 绘制超过 180f 的部分
                            canvas.nativeCanvas.drawArc(
                                -radius, // 左边界
                                -radius, // 上边界
                                radius,  // 右边界
                                radius,  // 下边界
                                180f,   // 起始角度
                                sweepAngle - 180f, // 扫过的角度（超过 180f 的部分）
                                false,  // 不使用中心点连接
                                lightPaint.asFrameworkPaint() // 使用浅色 Paint
                            )
                        }
                    }

                    //绘制小箭头
                    val canvasSize = size
                    val centerX = canvasSize.width / 2
                    val centerY = canvasSize.height
                    val radius = maxRadius - index * ringWidth * 1.2f // 旋转半径
//                    val radius = maxRadius - index * ringWidth * 2.05f // 旋转半径
                    val startAngle = 0f // 起始角度（以度为单位）
                    val sweepAngle2 = 180f+80f// 扫过的角度（以度为单位）
//                    val sweepAngle = sweepAngle - 180f - 25f  // 扫过的角度（以度为单位）

                    // 计算圆弧的终点坐标
                    val endAngle = startAngle + sweepAngle - (index +0.8f)*10f
                    val endAngleRadians = Math.toRadians(endAngle.toDouble())
                    val endX = centerX + radius * cos(endAngleRadians).toFloat()
                    val endY = centerY + radius * sin(endAngleRadians).toFloat()
                    // 绘制箭头
                    drawArrow(
                        drawScope = this, // 将 DrawScope 作为参数传递
                        startX = endX,
                        startY = endY,
                        angle = endAngle + 100f, // 箭头的旋转角度增加 90 度  箭头自身旋转
                        arrowHeadLength = 30f, // 箭头头部的长度
                        arrowHeadAngle = 30f, // 箭头头部的角度
//                        color = lightenColor(lightenColor(paint.color))
                    )

                }
            }
        }

        // 将颜色变浅的函数

    }
}



fun lightenColor(color: Color): Color {
    val red = (color.red * 1.3f).coerceAtMost(1f) // 增加红色分量
    val green = (color.green * 1.3f).coerceAtMost(1f) // 增加绿色分量
    val blue = (color.blue * 1.3f).coerceAtMost(1f) // 增加蓝色分量
    return Color(red, green, blue, color.alpha) // 保持透明度不变
}
fun darkenColor(color: Color, factor: Float = 0.5f): Color {
    // 混合黑色，factor 是黑色的混合比例（0.0f 为原始颜色，1.0f 为纯黑色）
    val red = color.red * (1 - factor)
    val green = color.green * (1 - factor)
    val blue = color.blue * (1 - factor)
    return Color(red, green, blue, color.alpha) // 保持透明度不变
}

//Canvas绘制当日数据图像
@Composable
fun SemiCircularRing(modifier: Modifier = Modifier.height(200.dp)) {
    // 定义半环形的宽度和颜色
    val ringWidth = 20.dp
    val ringColor = Color.Blue

    Canvas(
        modifier = Modifier
            .size(200.dp) // 设置 Canvas 的大小\
            .height(100.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // 计算半环形的半径
        val radius = (canvasWidth / 2) - ringWidth.toPx() / 2

        // 定义画笔
        val paint = Paint().apply {
            color = Color.Red
//                        style = android.graphics.Paint.Style.STROKE
            strokeWidth = ringWidth.toPx()
            isAntiAlias = true
        }

        // 绘制半环形
        drawIntoCanvas { canvas ->
            withTransform({
                // 将坐标系移动到 Canvas 的中心
                translate(canvasWidth / 2, canvasHeight / 2)
            }) {
                // 绘制圆弧
                canvas.drawArc(
                    rect = Rect(
                        -radius,
                        -radius,
                        radius,
                        radius
                    ),
                    startAngle = 180f, // 起始角度（从左侧开始）
                    sweepAngle = 180f, // 扫过的角度（180 度表示半圆）
                    useCenter = false, // 不使用中心点连接
                    paint = Paint()
                )
            }
        }
    }
}
//TODO: 当日数据

data class VitalityDataOfDay(val pic:Int,val name:String,var data:Int,val afterData:String)

@Composable
fun dataPart(){
    var calorie = 253
    var feet = 253
    var time = 253

    var data = VitalityDataOfDay (pic = 0,name = "",data = 1,afterData = "")
    var datas = arrayOf(
        VitalityDataOfDay (pic = R.mipmap.calorie,name = "卡路里（千卡）",data =calorie,afterData = "/400"),
        VitalityDataOfDay (pic = R.mipmap.shoe,name = "步数（步）",data =feet,afterData = "/400"),
        VitalityDataOfDay (pic = R.mipmap.clock,name = "中高强度（分）",data = time,afterData = "/400")
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for( data in datas){
            vitalityCard2(data,
                modifier = Modifier
                    .weight(1f)
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
fun vitalityCard2(data: VitalityDataOfDay,modifier: Modifier = Modifier) {
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

data class VitalityDataOfCalories( var day: Int, var data:Int)

@Composable
fun vitalityCard3(
     pic :Int = R.mipmap.calorie,
     name :String = "卡路里（千卡）",
     a : Boolean = true,
     data:List<Float> =  listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f),
     mycolor: Color = Color.Yellow
){
//    var vitalityDataOfCalories = VitalityDataOfCalories()
    var datas = arrayOf(
        VitalityDataOfCalories(1,340),
        VitalityDataOfCalories(2,444),
        VitalityDataOfCalories(3,380),
        VitalityDataOfCalories(4,717),
        VitalityDataOfCalories(5,419),
        VitalityDataOfCalories(6,0),
        VitalityDataOfCalories(7,0)
    )
//    val data :List<Float> = data// 对应周一至周日的数据

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
            modifier = Modifier
                .padding(10.dp)
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

            HealthBarChartWithInteractionsAndImage(
                data = data, // 对应周一至周日的数据
                mycolor = mycolor
            )

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
fun HealthBarChartWithInteractionsAndImage(
    data :List<Float> = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f), // 对应周一至周日的数据
    mycolor:Color = Color.Yellow
) {
    // 数据
//    val data = listOf(140f, 105f, 70f, 35f, 0f, 50f, 90f) // 对应周一至周日的数据
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
//    val mycolor = Color.Yellow

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

                            // 判断是否点击了柱子
                            if (index in data.indices) {
                                val left = index * (barWidth + gapWidth) + gapWidth / 2
                                val right = left + barWidth
                                if (offset.x in left..right) {
                                    clickedBarIndex = index
                                } else {
                                    clickedBarIndex = null
                                }
                            } else {
                                clickedBarIndex = null
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
                        color = mycolor,
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



@Composable
fun MyGraphWithArrowAtEnd() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.LightGray)
    ) {
        val canvasSize = size
        val centerX = canvasSize.width / 2
        val centerY = canvasSize.height / 2
        val radius = 200f // 旋转半径
        val startAngle = 180f // 起始角度（以度为单位）
        val sweepAngle = 90f  // 扫过的角度（以度为单位）
        val rotationRadius = 150f

        // 计算圆弧的终点坐标
        val endAngle = startAngle + sweepAngle
        val endAngleRadians = Math.toRadians(endAngle.toDouble())
        val endX = centerX + radius * cos(endAngleRadians).toFloat()
        val endY = centerY + radius * sin(endAngleRadians).toFloat()

        // 绘制箭头
        drawArrow(
            drawScope = this, // 将 DrawScope 作为参数传递
            startX = endX,
            startY = endY,
            angle = endAngle + 90f, // 箭头的旋转角度增加 90 度  箭头自身旋转
            arrowHeadLength = 30f, // 箭头头部的长度
            arrowHeadAngle = 30f, // 箭头头部的角度
            color = Color.Red
        )
    }
}

/**
 * 在指定位置绘制箭头
 *
 * @param drawScope DrawScope 对象，用于调用绘图方法
 * @param startX 箭头的起点 X 坐标
 * @param startY 箭头的起点 Y 坐标
 * @param angle 箭头的旋转角度（以度为单位）
 * @param arrowHeadLength 箭头头部的长度
 * @param arrowHeadAngle 箭头头部的角度（以度为单位）
 * @param color 箭头颜色
 */
fun drawArrow(
    drawScope: DrawScope,
    startX: Float,
    startY: Float,
    angle: Float,
    arrowHeadLength: Float,
    arrowHeadAngle: Float,
    color: Color = Color.Blue
) {
    // 计算箭头的终点坐标
    val angleRadians = Math.toRadians(angle.toDouble())
    val endX = startX + arrowHeadLength * cos(angleRadians).toFloat()
    val endY = startY + arrowHeadLength * sin(angleRadians).toFloat()

    // 计算箭头头部的两个端点
    val headAngleRadians = Math.toRadians(arrowHeadAngle.toDouble())
    val dx1 = arrowHeadLength * cos(angleRadians + headAngleRadians).toFloat()
    val dy1 = arrowHeadLength * sin(angleRadians + headAngleRadians).toFloat()
    val dx2 = arrowHeadLength * cos(angleRadians - headAngleRadians).toFloat()
    val dy2 = arrowHeadLength * sin(angleRadians - headAngleRadians).toFloat()

    // 绘制箭头头部的第一条斜线
    drawScope.drawLine(
        color = color,
        start = Offset(endX, endY),
        end = Offset(endX - dx1, endY - dy1),
        strokeWidth = 10f
    )

    // 绘制箭头头部的第二条斜线
    drawScope.drawLine(
        color = color,
        start = Offset(endX, endY),
        end = Offset(endX - dx2, endY - dy2),
        strokeWidth = 10f
    )
}

@Preview
@Composable
fun previewVitalityPartTest2() {
    myGraph()
//    MyGraphWithArrowAtEnd()
}