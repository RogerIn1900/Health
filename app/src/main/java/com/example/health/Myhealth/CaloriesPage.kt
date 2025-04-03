package com.example.health.Myhealth

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.health.R

@Composable
fun CaloriesPage() {
    Column {
        //TODO:日、周、月视图切换
        Row {
            Card {

            }
        }

        //TODO:对应视图的图表
        Row {

        }

        //TODO:对应视图的概览
        Card {
            Column {
                Row {
                    //TODO:图标
                    //TODO:今日概览
                }
                //TODO:分界线
                Column {
                    //TODO:消耗
                    //TODO：今日消耗
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
