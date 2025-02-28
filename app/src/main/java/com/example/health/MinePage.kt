package com.example.health

import android.graphics.fonts.FontStyle
import android.provider.ContactsContract.CommonDataKinds.Im
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.ui.theme.HealthTheme
import com.example.health.Database.Mine


@Composable
fun MinePage(){
    HealthTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp) // Add space between items
        ){
            item {
                MinePart1()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart2(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                MinePart3()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart4()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart5()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart6()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart7()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart8()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart9()
            }
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
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ){
            Image(
                painter = painterResource(id = R.mipmap.sleep ),
                contentDescription = "head",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text("课程会员")
//                Spacer(modifier = Modifier.height(2.dp))
                Text("开通会员畅练千节好课", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button (onClick = {}) {
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
            modifier = Modifier.padding(10.dp)
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,  //j均匀分布
            ) {
                MineCard4()
                MineCard4()
                MineCard4()
                MineCard4()
            }
        }
    }
}


@Composable
fun MinePart6() {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(130.dp) // 设置 Card 尺寸

    ) {
        Box(modifier = Modifier.fillMaxSize()
            .padding(start = 10.dp,end = 5.dp)

        ) {
            // 设置背景图片
            Image(
                painter = painterResource(id = R.mipmap.heart_white), // 替换为你的图片资源
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            // 在这里添加 Card 的内容
            Column {
                Text("广告", modifier = Modifier
                    .fillMaxWidth(),
                        textAlign = TextAlign.End // 设置文本右对齐,
                    , fontSize = 12.sp
                )
                Text("运动健康装备GO", fontSize = 20.sp)
                Text("智能好物带回家", fontSize = 15.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically, // 垂直居中

                ) {
                    Text("立即查看", fontSize = 16.sp)
                    Image(
                        painter = painterResource(id = R.mipmap.arrow_light),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MinePart7() {
//    TODO("Not yet implemented")
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(130.dp) // 设置 Card ����
            .fillMaxWidth()

    ) {
        Column (
            modifier = Modifier.padding(10.dp)
        ){
            Text("健康问诊", fontSize = 20.sp, fontWeight = FontWeight(10))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.sleep),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text("平安健康", fontSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.mipmap.arrow_light),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
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
        Text(content,
            modifier = Modifier.width(50.dp),
            maxLines = 2,
        )
    }
}


data class MineData(val imagePath: Int,val name:String ,val version :String  = "")
@Composable
fun MinePart8() {
//    TODO("Not yet implemented")
    val mineDatas = listOf(
        MineData(R.mipmap.sleep,"我的路线库"),
        MineData(R.mipmap.sleep,"App设置"),
        MineData(R.mipmap.sleep,"三方数据管理"),
        MineData(R.mipmap.sleep,"设备授权管理"),
        MineData(R.mipmap.sleep,"系统权限"),
        MineData(R.mipmap.sleep,"帮助与反馈"),
        MineData(R.mipmap.sleep,"设备共享"),
        MineData(R.mipmap.sleep,"版本更新","3.38.0"),
        MineData(R.mipmap.sleep,"关于"),
    )
    Card {
        Column (
        ){
            for (mineData in mineDatas){
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = mineData.imagePath),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(mineData.name)
                        if(mineData.version != ""){
                            Text(mineData.version, fontSize = 16.sp)
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.mipmap.arrow_light),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MinePart9() {
//    TODO("Not yet implemented")
}

@Preview
@Composable
fun PreviewMinePage() {
    HealthTheme {

        Column {
//        MinePart1()
//        MinePart2()
            MinePage()
//        MinePart6()
        }
    }
}