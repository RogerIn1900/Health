package com.example.health.TopDesign

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.health.R
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


@Composable
fun vitalityTop(navController: NavController,title: String = "活力指标") {

    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(title,
                    textAlign = TextAlign.Center,
                    )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("2025年3月5日",
                        fontSize = 12.sp
                        )
                    IconButton (onClick = {}) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "日期展开")
                    }
                }
            } },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "返回")
            }
        },
        actions = {
            Row {
                IconButton(onClick = { /* 处理点击事件 */ }) {
                    Icon(Icons.Default.Send, contentDescription = "更多")
                }
                IconButton(onClick = { /* 处理点击事件 */ }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "搜索")
                }
            }

        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black
        ),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    )
}

@Preview
@Composable
fun PreviewHomeTop() {
//    vitalityTop()
}