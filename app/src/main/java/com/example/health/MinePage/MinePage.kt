package com.example.health.MinePage

import android.app.Activity
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.health.R
import com.example.health.ui.theme.HealthTheme



@Composable
fun MinePage(navController: NavController ){
    HealthTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp) // Add space between items
        ){
            item {
                MinePart1(navController)     //个人信息、登陆界面
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
                Spacer(modifier = Modifier.height(10.dp))
                AppNavigation2()
                Spacer(modifier = Modifier.height(10.dp))
                AppNavigation()
            }
        }
    }
}



@Composable
fun MinePart1(navController : NavController) {
//    TODO("Mine information")
    val name:String ="deepseek";
    val sex:String = "男"
    val height  = 180
    val age = 18

    Row(
        modifier = Modifier.clickable {
            navController.navigate("LoginPage")
        }
    ){
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
fun MineCard(pic:Int = R.mipmap.sleep, title:String = "我的睡眠") {
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
                painter = painterResource(id = R.mipmap.sleep),
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
            painter = painterResource(id = R.mipmap.sleep),
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
    val navController = rememberNavController()//创建导航控制器
    NavHost(//设置导航容器
        navController = navController,
        startDestination = "main"
    ) {


        // 详情页
        composable("detail") {
            DetailScreen {
                navController.popBackStack("main", inclusive = false)
            }
        }

        // 主页
        composable("home") {
            HomeScreen2(
                onCardClick = {
                    navController.navigate("detail"){

                    }
                }
            )
        }

        // 主活动
        composable("main") {
            MainScreen()
        }
    }
}

//TODO:方法三，动画跳转


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // 主页
        composable("home") {
            HomeScreen(
                onNavigate = { navController.navigate("fullscreen") }
            )
        }

        // 全屏页面（带自定义动画）
        composable(
            route = "fullscreen",
            enterTransition = {
                slideInHorizontally(animationSpec = tween(300)) { fullWidth ->
                    fullWidth  // 从右侧滑入
                } + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                slideOutHorizontally(animationSpec = tween(300)) { fullWidth ->
                    -fullWidth / 2  // 向左滑出
                } + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300))  // 返回时淡入
            },
            popExitTransition = {
                slideOutHorizontally(animationSpec = tween(300)) { fullWidth ->
                    fullWidth  // 向右滑出
                } + fadeOut(animationSpec = tween(300))
            }
        ) {
            FullScreenDestination(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
@Composable
fun HomeScreen(onNavigate: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("普通页面", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onNavigate,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("进入全屏模式", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
@Composable
fun FullScreenDestination(onBack: () -> Unit) {
    // 关键：覆盖整个屏幕的布局
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "全屏覆盖内容",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))

                // 返回按钮
                FilledTonalButton(
                    onClick = onBack,
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text("返回", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

//TODO:方法二，隐藏消息栏等

// 主导航结构
@Composable
fun AppNavigation2() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // 普通主页
        composable("home") {
            HomeScreen3(
                onNavigateToFullScreen = {
                    navController.navigate("fullscreen")
                }
            )
        }

        // 全屏页面
        composable("fullscreen") {
            FullScreenDestination2(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

// 主页内容
@Composable
fun HomeScreen3(onNavigateToFullScreen: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("普通主页", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onNavigateToFullScreen) {
                Text("进入全屏页面")
            }
        }
    }
}

// 全屏页面（核心实现）
@Composable
fun FullScreenDestination2(onBack: () -> Unit) {
    val context = LocalContext.current
    val activity = context as Activity

    // 关键：控制系统栏显示/隐藏
    DisposableEffect(Unit) {
        val window = activity.window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        // 进入时隐藏系统栏
        insetsController.hide(WindowInsetsCompat.Type.systemBars())
        insetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        onDispose {
            // 退出时恢复系统栏
            insetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    // 全屏内容
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "全屏覆盖页面",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onBack) {
                    Text("返回普通页面")
                }
            }
        }
    }
}
//TODO:方法一，使用dialog

@Composable
fun MainScreen() {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { showDialog = true }) {
            Text("打开全屏页面")
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                FullScreenContent(onClose = { showDialog = false })
            }
        }
    }
}

@Composable
fun FullScreenContent(onClose: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
//            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("这是全屏覆盖页面", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onClose) {
                Text("关闭")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen2(onCardClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 可点击的卡片
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable(onClick = onCardClick),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("点击卡片跳转", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}

@Composable
fun DetailScreen(onBack: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("这是详情页面", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onBack
            ) {
                Text("返回主页")
            }
        }
    }
}

@Preview
@Composable
fun PreviewMinePage() {
    HealthTheme {

        Column {
//        MinePart1()
//        MinePart2()
//            MinePage()
//        MinePart6()
        }
    }
}