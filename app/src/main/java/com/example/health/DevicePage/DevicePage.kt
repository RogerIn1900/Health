package com.example.health.DevicePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.R
import com.example.health.ui.theme.RingOrange

@Composable
fun DevicePage() {
    LazyColumn {
        item {
            PartDevice()
            Spacer(modifier = Modifier.padding(12.dp))
            PartAdvertisement1()
            Spacer(modifier = Modifier.padding(12.dp))
            PartMarket()
            Spacer(modifier = Modifier.padding(12.dp))
            PartAdvertisement2()
            Spacer(modifier = Modifier.padding(12.dp))
            PartSettings()
        }
    }
}

@Composable
fun PartDevice() {
    val handRingName = "小米手环9 NFC版"
    val deviceState by remember{ mutableStateOf("已连接") }
    val batteryState by remember{ mutableStateOf("31%") }
    val lastChargingTime by remember{ mutableStateOf(13) }
    var isSynchronizing by remember{ mutableStateOf(false)}

    Row {
        Image(
            painter = painterResource(R.mipmap.sleep),
            contentDescription = "手表当前样式图片",
            modifier = Modifier.weight(1f)
        )
        Column(
            modifier = Modifier.weight(1.2f)
                .padding(end = 12.dp)

        ) {
            Row {
                Text(
                    text = handRingName,
                    fontSize = 24.sp,
                    //加粗
//                    fontStyle =
                )
                Image(
                    painter = painterResource(R.mipmap.device_gray),
                    contentDescription = "设备管理按钮"
                )
            }
            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "{$deviceState} | 电量{$batteryState}，距上次充电已{$lastChargingTime}",
                //设置换行类型
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "切换佩戴模式",
                color = RingOrange,
                modifier = Modifier
                    .clickable {

                    }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )

            FilledTonalButton (
//                modifier = Modifier.background(RingOrange),
                onClick = {
                    isSynchronizing = true
                }
            ) {
                Text(
                    text = "同步",
                    color = RingOrange
                )
            }
        }

    }
}

@Composable
fun PartAdvertisement1() {
    Card() {
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(6.dp)
        )
        {
            Image(
                painter = painterResource(R.mipmap.sleep),
                contentDescription = "广告",
                modifier = Modifier.height(48.dp)
            )
            Image(
                painter = painterResource(R.mipmap.clock),
                contentDescription = " ",
                modifier = Modifier.size(18.dp)
                    .align(Alignment.TopEnd)
            )
        }

    }
}


data class Dial(val resource:Int,val name:String)


@Composable
fun PartMarket() {
    var hasNewDial by remember { mutableStateOf(true) }
    val newDialNumber by remember{ mutableStateOf(75) }
    val itemsList = arrayOf(
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),

    )

    Card (
    ){
        Column (
            modifier = Modifier.padding(12.dp)

        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "表盘市场",
                    fontSize = 24.sp
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "最近上新{$newDialNumber}",
                    color = Color.Gray,
                )

                if(hasNewDial){
                    Image(
                        painter = painterResource(R.mipmap.sleep),
                        contentDescription = "如果有上新的表盘，就显示hong di a",
                        modifier = Modifier.height(24.dp)

                    )
                }
                Image(
                    painter = painterResource(R.mipmap.arrow_light),
                    contentDescription = "跳转至市场",
                    modifier = Modifier.size(24.dp)

                )
            }
            LazyRow {
                itemsIndexed(itemsList){ index, item ->
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(item.resource),
                            contentDescription = " ",
                            modifier = Modifier.width(36.dp)
                        )
                        Text(
                            text = item.name,
                            fontSize = 12.sp
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun PartAdvertisement2() {
    Card() {
        Box(
            modifier = Modifier.fillMaxSize()

        )
        {
            Image(
                painter = painterResource(R.mipmap.sleep),
                contentDescription = "广告",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = "广告",
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

    }
}

@Composable
fun PartSettings() {
    val itemsList = arrayOf(
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),
        Dial(R.mipmap.sleep,"心情感应"),

        )
    Card(
    ) {
        Column(
            modifier = Modifier.padding(12.dp)


        ) {
            for (item in itemsList){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(6.dp)
                ){
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(RingOrange)
                            .size(36.dp)
                    ){
                        Image(
                            painter = painterResource(item.resource),
                            contentDescription = " ",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }

                    Text(
                        text = item.name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(R.mipmap.arrow_light),
                        contentDescription = " ",
                        modifier = Modifier.size(24.dp)
                    )
                }

            }
        }
    }

}

@Preview
@Composable
fun PreviewParts(){
    DevicePage()
}