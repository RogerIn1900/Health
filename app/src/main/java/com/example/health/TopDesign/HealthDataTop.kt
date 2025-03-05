package com.example.health.TopDesign

import android.media.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeTop(title: String){
    TopAppBar(title = { Text(title.toString(),modifier = Modifier.size(36.dp)) })
}

@Composable
fun a() {
    val title :String = "s"
    CenterAlignedTopAppBar(
        modifier = Modifier.wrapContentSize(),
        title = {
            Column(
            ) {
                Text("My App", fontSize = 40.sp)
                Text("2025年3月5日")
            } },

        actions = {
            IconButton(onClick = { /* 处理点击事件 */ }) {
                Icon(Icons.Default.Search, contentDescription = "搜索")
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* 处理点击事件 */ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "返回")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.White
        ),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    )
}

@Preview
@Composable
fun PreviewHomeTop() {
    a()
}