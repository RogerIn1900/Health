package com.example.health.Myhealth

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalityIndex(navController:NavController) {
    Column {
        weekGraph()
        vitalityPart1()
        vitalityPart2()
        vitalityPart3()

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
    TODO("Not yet implemented")
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



@Preview
@Composable
fun previewVitalityPartTest2() {
//    vitalityPartTest2()
    ChartScreen()
}