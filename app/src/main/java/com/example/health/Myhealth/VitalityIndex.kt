package com.example.health.Myhealth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.health.MineCard2
import com.example.health.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalityIndex(navController:NavController) {
//TODO: 一周日期图表
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for( i in 1..7){
            VitalityCard(i, modifier = Modifier.weight(1f))
        }
    }
//TODO: 当日图表
//TODO: 当日数据
//TODO: 卡路里图表
//TODO: 步数图表
//TODO: 中高强度运动图表
//TODO: 活力成就


}

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
