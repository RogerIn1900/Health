package com.example.health.Myhealth

import android.content.Intent
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.health.ui.theme.HealthTheme
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.example.health.R
import com.example.health.ui.theme.sleep_background

@Composable
fun MyHealth(navController:NavController) {
    HealthTheme {
        LazyColumn {
            item {
                Box(modifier = Modifier.fillMaxSize()
                    .padding(bottom = 80.dp)
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.background),
//                        contentDescription = "nothing",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(228.dp)
//                            .clickable {
//                                navController.navigate("Vitality")
//                            }
//                    )
                    myGraph(modifier = Modifier
                        .clickable {
                            navController.navigate("Vitality")
                        }
                        .align(Alignment.Center)
                        .size(300.dp,150.dp)


                    )

                }
                Part2(navController)
                GridLayoutExample()
            }
        }
    }
}

//part1
@Composable
fun Part2(navController:NavController) {
    Card(
        modifier = Modifier
//            .weight(1f) // 使用 Modifier.weight()
            .padding(12.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
//               .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TODO：卡路里/步数/中高强度 card
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // TODO: 自定义小卡片a 放入响应的数据
                Cart(R.mipmap.calorie, "卡路里", 85, "/400千卡",navController)
                Cart(R.mipmap.shoe, "步数", 2317, "/6000步",navController)
                Cart(R.mipmap.clock, "中强度", 24, "/30分钟",navController)

            }
            // TODO: 分割线
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            // TODO：有效站立
            Row {
                // TODO: 图标
                Image(
                    painter = painterResource(id = R.mipmap.stand),
                    contentDescription = "Favorite Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp)) // 添加间隔
                Text("有效站立活动 0 次")
                // TODO：题词

                Spacer(modifier = Modifier.weight(1f)) // 添加间隔
                Image(
                    painter = painterResource(id = R.mipmap.arrow_dark),
//                    imageVector = Icons.Default.Add,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier.size(24.dp)
                )

            }
        }

    }
}

@Composable
fun CartA() {
    Column {
        // TODO: 图标、文字、跳转图标
        Row {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(24.dp)
            )
            Text("卡路里")
            Spacer(modifier = Modifier.width(8.dp)) // 添加间隔
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(24.dp)
            )
        }
        // TODO: 变动数据
        Text("0", fontSize = 64.sp)
        // TODO: 达标数值
        Text("/400千卡")
    }
}

@Composable
fun Cart(
    source: Int = R.drawable.fire,
//    contentDescription:String = "nothing",
    name: String = "卡路里",
    data: Int = 0,
    dataLimit: String = "/400步",
    navController: NavController
) {
    val page = when(name){
        "卡路里" ->{
            "CaloriesPage"
        }
        "步数" ->{
            "StepNumber"
        }
        "中强度" ->{
            "MidActivity"
        }
        else ->{
            "CaloriesPage"
        }
    }
    Column(
        modifier = Modifier
            .width(112.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate( page)
            },

    ) {
        // TODO: 图标、文字、跳转图标
        Row {
            Image(
                painter = painterResource(id = source),
                contentDescription = "nothing",
                modifier = Modifier.size(24.dp)
            )
            Text(name, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp)) // 添加间隔
            Image(
                painter = painterResource(id = R.mipmap.arrow_dark),
//                imageVector = Icons.Default.Add,
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(14.dp)
            )
        }
        // TODO: 变动数据
        Text(data.toString(), fontSize = 36.sp)
        // TODO: 达标数值
        Text(dataLimit, fontSize = 14.sp)
    }
}

@Composable
fun Part1() {

}


// TODO: part3
//基本结构
@Composable
fun Part3() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")

    LazyVerticalGrid(

        columns = GridCells.Fixed(2), // 每行显示 2 列
//        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(items) { item ->
//            Card(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//            ) {
//                Text(text = item, modifier = Modifier.padding(16.dp))
//            }
            CardMy()
        }
    }
}


@Composable
fun GridLayoutExample() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6","Item 7","Item 8")
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        // 使用 Row 来创建每一行
        for (i in items.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // 行间距
            ) {
                // 第一列
                Card(
                    modifier = Modifier.weight(1f),
                ) {
//                    Text(text = items[i], modifier = Modifier.padding(16.dp))
                    CardMy()
                }

                // 检查是否有第二列
                if (i + 1 < items.size) {
                    Card(
                        modifier = Modifier.weight(1f),
                    ) {
//                        Text(text = items[i + 1], modifier = Modifier.padding(16.dp))
                        CardMy()
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp)) // 行间距
        }
    }
}


//@Composable
//fun Part3() {
//    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")
//
//}

@Composable
fun CardA() {
    HealthTheme {
        Column {
            Icon(
                painter = painterResource(id = R.drawable.bed),
                contentDescription = "bed",
                modifier = Modifier.size(24.dp)
            )

            Text("睡眠")
            Row {
                Text("探索您的睡眠动物")
                Icon(
                    painter = painterResource(id = R.drawable.head),
                    contentDescription = "head",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "head",
                modifier = Modifier.size(24.dp)
            )
            Row {
                Text("有待提高")
                Spacer(modifier = Modifier.weight(1f))
                Text("优")
            }
        }
    }
}


@Composable
fun CardMy(
    source01: String = R.mipmap.sleep.toString(),
    source02: String = R.drawable.fire.toString(),
    text01: String = "睡眠",
    text02: String = "探索您的睡眠动物",
    data: Int = 0,
    left: String = "有待提高",
    right: String = "优"
) {
    Card(
        modifier = Modifier
                    .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                   .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(sleep_background)
            ){
                Image(
                    painter = painterResource(id = source01.toInt()),
                    contentDescription = "nothing",
                    modifier = Modifier
                        .fillMaxSize()
                        .size(24.dp)
                )
            }


            Text(text01)
            Row {
                Text(text02)
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
//                painter = painterResource(id = R.drawable.head),
                    imageVector = Icons.Default.Add,
                    contentDescription = "head",
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Image(
                painter = painterResource(id = source02.toInt()),
                contentDescription = "head",
                modifier = Modifier.size(24.dp)
            )
            Row {
                Text(left)
                Spacer(modifier = Modifier.weight(1f))
                Text(right)
            }
        }
    }

}


@Preview
@Composable
fun PreviewPart1() {
    HealthTheme {
        Column {
            Part1()
//            Part2(navController = NavController(context))
            GridLayoutExample()
        }
    }
}