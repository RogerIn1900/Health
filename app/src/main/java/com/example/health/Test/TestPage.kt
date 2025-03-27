package com.example.health.Test

import android.content.res.Resources.Theme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.health.ui.theme.HealthTheme

@Composable
fun TestPage(){
    //生成数据
    val temp by remember { mutableStateOf(36.5f) }
    val rate by remember { mutableStateOf(80) }
    val pressure1 by remember { mutableStateOf(100) }
    val pressure2 by remember { mutableStateOf(70) }
    val state by remember { mutableStateOf(false) }

    //传入数据生成页面
    if(!state){
        HealthTheme {
            Surface (
                modifier = Modifier.fillMaxSize(),
            ) {
                Column {
                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val canvasSize = size
                        val canvasWidth = canvasSize.width
                        val canvasHeight = canvasSize.height

                        val ringWidth = 40.dp.toPx()
                        val maxRadius = (canvasWidth / 2) - ringWidth / 2
                    }
                }
            }
        }
    }
}

@Composable
fun <T> RememberGenericExample(value: T) {
    // 提供明确的类型参数
    val state by remember { mutableStateOf<T>(value) }

    // 或者不使用委托语法
//    val state = remember { mutableStateOf(value) }
//    val currentValue = state.value
    Column {
        Text(state.toString())
    }
}

@Preview
@Composable
fun TestPagePreview(){

}