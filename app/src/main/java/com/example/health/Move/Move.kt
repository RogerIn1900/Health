package com.example.health.Move

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.DevicePage.Dial
import com.example.health.Myhealth.Cart
import com.example.health.R
import com.example.health.ui.theme.RingOrange

@Composable
fun Move() {
    OptionPart()
    Spacer(modifier = Modifier.padding(12.dp))
    ActionRecord()
    Spacer(modifier = Modifier.padding(12.dp))
    TrainingMetrics()
    Spacer(modifier = Modifier.padding(12.dp))
    RunningGroup()
    Spacer(modifier = Modifier.padding(12.dp))
    TrainingPlan()
    Spacer(modifier = Modifier.padding(12.dp))
    ActivityRecommendation()
    Spacer(modifier = Modifier.padding(12.dp))
    CourseContent()
    Spacer(modifier = Modifier.padding(12.dp))
    DancePart()
    Spacer(modifier = Modifier.padding(12.dp))
    TrainingCourse()
    Spacer(modifier = Modifier.padding(12.dp))
    InteractionCourse()
    Spacer(modifier = Modifier.padding(12.dp))
}

data class ActivityOption(val resources: Int,val name: String)

@Composable
fun OptionPart() {
    var itemList = arrayOf(
        ActivityOption(R.mipmap.sleep,"户外运动"),
        ActivityOption(R.mipmap.clock,"健走"),
        ActivityOption(R.mipmap.sleep,"户外骑行"),
        ActivityOption(R.mipmap.sleep,"室内跑步"),
        ActivityOption(R.mipmap.sleep,"跳绳"),
        ActivityOption(R.mipmap.sleep,"动感单车"),
        ActivityOption(R.mipmap.sleep,"睡觉"),
        ActivityOption(R.mipmap.sleep,"睡觉"),
        ActivityOption(R.mipmap.sleep,"睡觉"),
        ActivityOption(R.mipmap.sleep,"睡觉"),
        ActivityOption(R.mipmap.sleep,"睡觉")
    )
    LazyRow {
        itemsIndexed(itemList){index, item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp)
            ) {
                Image(
                    painter = painterResource(R.mipmap.shoe),
                    contentDescription = " ",
                    modifier = Modifier.clip(CircleShape)
                )
                Text(
                    text = "${item.name}",
                    color = Color.White
                )

            }
        }
    }
}

@Composable
fun ActionRecord() {
    val activityName = " 户外骑行"
    val exerciseVolume = "11.34公里"
    Card() {
        Column {
            Row (
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "运动记录",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "全部",
                    fontSize = 20.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(R.mipmap.arrow_light),
                    contentDescription = "",
                    modifier = Modifier.size(12.dp)
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp)
            Row {
                Image(
                    painter = painterResource(R.mipmap.sleep),
                    contentDescription = " "
                )
                Column {
                    Text(
                        text = "${activityName}  ${exerciseVolume}",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    val exerciseTime = "41分22秒"
                    val exerciseCalories = "370千卡"
                    val exerciseDate = "4月16日"

                    Text(
                        text = "${exerciseTime} . ${exerciseCalories} . ${exerciseDate}",
                        fontSize = 20.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

//训练指标
@Composable
fun TrainingMetrics() {
    val sportLoad by remember { mutableStateOf(57) }
    val sportDate by remember { mutableStateOf( "4月23日") }
    val sportRate by remember { mutableStateOf("良好") }

    Card(
        modifier = Modifier.padding(12.dp)
//            .background(Color.Blue)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Blue)
                .padding(start = 6.dp, end = 6.dp, top = 12.dp, bottom = 12.dp)
//                .fillMaxSize()
        ) {
            Text(
                text = "训练指标",
                fontSize = 24.sp,
                color = Color.White
            )

            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Card(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(
                            text = "训练负荷",
                            fontSize = 24.sp,
                            color = Color.White
                        )
                        Text(
                            text = "${sportLoad}",
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        Text(
                            text = "${sportDate} ${sportRate}",
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                        //进度条
                        Divider(
                            modifier = Modifier
                                .padding(12.dp)
                        )
                        Row {
                            Text(
                                text = "不足",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "过量",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Card(
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp)
                    ) {
//                        val maxOxygenUptake = ""
                        Text(
                            text = "最大摄氧量",
                            fontSize = 24.sp,
                            color = Color.White
                        )

//                        Spacer(modifier = Modifier.weight(1f))


                        Text(
                            text = "佩戴设备跑步后评估",
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                        //进度条
                        Divider(
                            modifier = Modifier
                                .padding(12.dp)
                        )
                        Row {
                            Text(
                                text = "新手",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            Text(
                                text = "超群",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

        }

    }
}

@Composable
fun RunningGroup() {
    Card() {
        Column (
            modifier = Modifier.padding(12.dp)
        ){
            Text(
                text = "运动跑团",
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier.padding(6.dp)
            ){
                Card (){
                    Image(
                        painter = painterResource(R.mipmap.sleep),
                        contentDescription = " ",
                        modifier = Modifier.background(RingOrange)
                            .fillMaxWidth()
                    )

                }
                Column(
                    modifier = Modifier.align(Alignment.TopStart)
                        .padding(6.dp)
                ) {
                    Text(
                        text = "跑步团",
                        color = Color.White
                    )
                    Text(
                        text = "立即加入.共享运动乐趣",
                        color = Color.White

                    )
                }
            }
        }
    }
}

//训练计划
@Composable
fun TrainingPlan() {
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
    Card() {
        Column(

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(6.dp)
            ) {
                Text(
                    text = "训练计划",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "全部",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Image(
                    painter = painterResource(R.mipmap.arrow_light),
                    contentDescription = " ",
                    modifier = Modifier.size(12.dp)
                )
            }

            LazyRow {
                itemsIndexed(itemsList){index, item ->
                    Box(
                        modifier = Modifier.padding(6.dp)
                    ){
                        Card {
                            Image(
                                painter = painterResource(item.resource),
                                contentDescription = " ",
                                modifier = Modifier.background(RingOrange)
                                    .fillMaxWidth()
                            )
                        }
                        Text(
                            text = "${item.name}",
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun ActivityRecommendation() {

}

@Composable
fun CourseContent() {

}

@Composable
fun DancePart() {

}

@Composable
fun TrainingCourse() {

}

@Composable
fun InteractionCourse() {

}


@Preview
@Composable
fun MovePreview(){
    TrainingPlan()
}