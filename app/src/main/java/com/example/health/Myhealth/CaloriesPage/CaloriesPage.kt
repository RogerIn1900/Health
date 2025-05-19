package com.example.health.Myhealth.CaloriesPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.example.Screen
import com.example.health.R

@Composable
fun CaloriesPage() {
    Column {
        //TODO:日、周、月视图切换
        Row (
            modifier = Modifier.padding(10.dp)
        ) {
            navBar()
        }

        //TODO:对应视图的图表
        Column {
            Row {
                Text("2273", fontSize = 40.sp)
                Text("千卡")
            }
            Row {
                Text("2025年3月31日至4月6日", color = Color.Gray)
            }
            Row {
                Image(
                    painter = painterResource(id = R.mipmap.calorie),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        //TODO:对应视图的概览
        Card {
            Column {
                Row {
                    //TODo:图标
                    Image(
                        painter = painterResource(id = R.mipmap.calorie),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                    //TOD:今日概览
                    Text("本周概览")
                }
                //TODO:分界线
                Divider(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    thickness = 0.5.dp,
                    modifier = Modifier.fillMaxWidth()
                        .padding(10.dp)
                )

                Row {
                    Row {
                        Column {
                            //TODO:消耗
                            Row {
                                Text("2513",fontSize = 40.sp)
                                Text("千卡")
                            }
                            Text("总消耗")
                            //TODO：今日消耗
                        }
                        Spacer(modifier = Modifier.fillMaxWidth())
                    }
                    Row {
                        Column {
                            //TODO:消耗
                            Row {
                                Text("2513",fontSize = 40.sp)
                                Text("千卡")
                            }
                            Text("总消耗")
                            //TODO：今日消耗
                        }
                        Spacer(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Card {
            Row {
                Text("了解卡路里")
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.mipmap.calorie),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


@Composable
fun navBar() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val title :String = when (currentRoute){
        Screen.Dormitory.route -> stringResource(id = R.string.dormitory)
        Screen.Rest02.route  ->  stringResource(id = R.string.maintenance)
        Screen.NoticeBoardScreen.route  ->  stringResource(id = R.string.NoticeBoardScreen)
        Screen.Mine.route  ->  stringResource(id = R.string.mine)
        else -> "Unknown"
    }

}

@Preview
@Composable
fun CaloriesPagePreview(){
    CaloriesPage()
}