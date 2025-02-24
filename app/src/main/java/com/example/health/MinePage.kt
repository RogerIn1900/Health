package com.example.health

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
//        MinePart3()
//        MinePart4()
//        MinePart5()
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
    TODO("Not yet implemented")
}

@Composable
fun MinePart4() {
    TODO("Not yet implemented")
}

@Composable
fun MinePart5() {
    TODO("Not yet implemented")
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