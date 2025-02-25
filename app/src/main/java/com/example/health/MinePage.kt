package com.example.health

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.ui.theme.HealthTheme

@Composable
fun MinePage(){
    HealthTheme {
        Column {
            MinePart1()
            Spacer(modifier = Modifier.height(5.dp))
            MinePart2(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(10.dp))
            MinePart3()
            Spacer(modifier = Modifier.height(10.dp))
            MinePart4()
            Spacer(modifier = Modifier.height(10.dp))
            MinePart5()
        }

    }
}

@Composable
fun MinePart1() {
//    TODO("Mine information")
    val name:String ="deepseek";
    val sex:String = "男"
    val height  = 180
    val age = 18

    Row(){
        Image(
            painter = painterResource(id = R.mipmap.stand),
            contentDescription = "nothing",
            modifier = Modifier.size(60.dp)
        )
        Column {
            Text(name, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(sex, fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(height.toString()+"厘米", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(age.toString()+"岁", fontSize = 20.sp)

            }
        }
    }
}

@Composable
fun MinePart2(modifier:Modifier = Modifier) {
//    TODO("Not yet implemented")
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly, // 均匀分布
        verticalAlignment = Alignment.CenterVertically // 垂直居中
    ){
        MineCard()
        Spacer(modifier = Modifier.width(10.dp))
        MineCard()
        Spacer(modifier = Modifier.width(10.dp))
        MineCard()
        Spacer(modifier = Modifier.width(10.dp))
        MineCard()

    }
}

@Composable
fun MineCard(pic:Int = R.mipmap.sleep,title:String = "我的睡眠") {
//    TODO("Not yet implemented")
    Column (
        verticalArrangement = Arrangement.Center, // 垂直居中
        horizontalAlignment = Alignment.CenterHorizontally, // 水平居中
        modifier = Modifier

    ){
        Image(
            painter = painterResource(id = pic),
            contentDescription = "nothing",
            modifier = Modifier.size(36.dp)

        )
        Text(title)
    }
}

@Composable
fun MinePart3() {
//    TODO("Not yet implemented")
    Card {
        Row {
            Image(
                painter = painterResource(id = R.mipmap.sleep ),
                contentDescription = "head",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text("广告内容")
                Spacer(modifier = Modifier.height(5.dp))
                Text("info")
            }
            Spacer(modifier = Modifier.weight(1f))
            Card (
                modifier = Modifier.background(Color.Gray)

            ){
                Text("立即开通")
            }
        }
    }
}


@Composable
fun MinePart4() {
//    TODO("Not yet implemented")
    Row {
        Card (
            modifier = Modifier.weight(1f)
                .height(128.dp)
        ){
            MineCard2()
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.weight(1f)
                .height(128.dp)
        ) {
            MineCard3(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(5.dp))
            MineCard3(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun MineCard3(modifier: Modifier ) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ){
                Text("运动健康周报",modifier = Modifier.fillMaxWidth())
                Text("02.16 - 02.31",modifier = Modifier.fillMaxWidth())
            }
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.mipmap.sleep),
                contentDescription = null,
                modifier =  Modifier.size(45.dp)
                    .weight(1f)
            )
        }
    }


}

@Composable
fun MineCard2() {
    Column (
        modifier = Modifier.padding(8.dp)
    ){
        Text("小习惯", modifier = Modifier.fillMaxWidth())
        Text("加入打卡", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(id = R.mipmap.sleep ),
            contentDescription = "",
            modifier = Modifier.width(60.dp)

        )
    }
}

@Composable
fun MinePart5() {
//    TODO("Not yet implemented")
    Card(
//        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row {
                Text("我的勋章")
                Spacer(modifier = Modifier.weight(1f))
                Text("全部")
                Image(
                    painter = painterResource(id = R.mipmap.stand),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row{
            MineCard4()
            MineCard4()
            MineCard4()
            MineCard4()
                }
        }
    }
}

@Composable
fun MineCard4(
    pic:Int = R.mipmap.stand,
    content :String = "活力一周步数"
              ) {
    Column (
        verticalArrangement = Arrangement.Center, // 垂直居中
        horizontalAlignment = Alignment.CenterHorizontally, // 水平居中
    ){
        Image(
            painter = painterResource(id = R.mipmap.stand),
            contentDescription = "",

        )
        Text(content, modifier = Modifier.width(64.dp))
    }
}

@Preview
@Composable
fun PreviewMinePage() {
    Column {
//        MinePart1()
//        MinePart2()
        MinePage()
    }
}