package com.example.compose.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.health.IndeterminateCircularIndicator
import com.example.health.LinearDeterminateIndicator
import com.example.health.ViewModel.MainViewModel.MainViewModel
import com.example.health.MinePage
import com.example.health.Myhealth.CaloriesPage.CaloriesPage
import com.example.health.Myhealth.CaloriesPage.CaloriesPageDateView
import com.example.health.Myhealth.CaloriesPage.navMap
import com.example.health.Myhealth.MidActivity.MidActivity
import com.example.health.Myhealth.MyHealth
import com.example.health.Myhealth.StepNumber.StepNumber
import com.example.health.Myhealth.Vitality.VitalityDates
import com.example.health.Myhealth.Vitality.VitalityIndex
import com.example.health.PostPic.ImageUploaderScreen
import com.example.health.R
import com.example.health.TopDesign.CaloriesPageDateViewTop
import com.example.health.TopDesign.CaloriesPageTop
import com.example.health.TopDesign.HomeTop
import com.example.health.TopDesign.vitalityTop

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(viewModel: MainViewModel = MainViewModel()) {
    val navController = rememberNavController()
//    val isBottomVisible by viewModel.isBottomVisible.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val title :String = when (currentRoute){
        Screen.Health.route -> stringResource(id = R.string.health)
        Screen.Move.route  ->  stringResource(id = R.string.move)
        Screen.Service.route  ->  stringResource(id = R.string.service)
        Screen.Mine.route  ->  stringResource(id = R.string.mine)
        else -> "Unknown"
    }
    val isBottomBarVisible = when (currentRoute){
        Screen.Health.route -> true
        Screen.Move.route  -> true
        Screen.Service.route  ->  true
        Screen.Mine.route  ->  true
        Screen.CaloriesPage.route  ->  "CaloriesPage"
        else -> false
    }
    val isTopBarVisible = when (currentRoute){
        Screen.Health.route -> true
        Screen.Move.route  -> true
        Screen.Service.route  ->  true
        Screen.Mine.route  ->  true
        Screen.CaloriesPage.route  ->  "CaloriesPage"
        Screen.CaloriesPageDateView.route  ->  "CaloriesPageDateView"
        else -> false
    }


    Scaffold(
        topBar = {
//            TopAppBar(title = { Text(title.toString()) })
//            HomeTop(title)
            if(isTopBarVisible == true){

            }
            when(isTopBarVisible){
                true -> {
                    HomeTop(title)
                }
                false -> {
                    //活力Top测试
                    vitalityTop(navController)
                }
                "CaloriesPage" ->{
                    CaloriesPageTop(navController)
                }
                "CaloriesPageDateView" ->{
                    CaloriesPageDateViewTop(navController)
                }
            }


                 },
        bottomBar = {
            if (isBottomBarVisible == true)BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Health.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Health.route,
//                enterTransition = {
//                    slideInHorizontally(animationSpec = tween(300)) { fullWidth ->
//                        fullWidth  // 从右侧滑入
//                    } + fadeIn(animationSpec = tween(300))
//                },
//                exitTransition = {
//                    slideOutHorizontally(animationSpec = tween(300)) { fullWidth ->
//                        -fullWidth / 2  // 向左滑出
//                    } + fadeOut(animationSpec = tween(300))
//                },
//                popEnterTransition = {
//                    fadeIn(animationSpec = tween(300))  // 返回时淡入
//                },
//                popExitTransition = {
//                    slideOutHorizontally(animationSpec = tween(300)) { fullWidth ->
//                        fullWidth  // 向右滑出
//                    } + fadeOut(animationSpec = tween(300))
//                }

            )
            { MyHealth(navController) }

            navMap(navController)





// 2. 跳转时传递参数
//            navController.navigate("detail_screen/123")
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Health,
        Screen.Move,
        Screen.Service,
        Screen.Mine
    )

    //map的使用不对，map的键是Screen类型，但是调用map内容的时候是String类型
    val pic_unselected = mapOf(
        "health" to R.mipmap.health_gray,
        "move" to R.mipmap.move_gray,
        "service" to R.mipmap.device_gray,
        "mine" to R.mipmap.mine_gray
    )

    val pic_selected = mapOf(
        "health" to R.mipmap.health_orange,
        "move" to R.mipmap.move_orange,
        "service" to R.mipmap.device_orange,
        "mine" to R.mipmap.mine_orange
    )

    //不同类型的对象不能进行比较screen是一个Screen对象和navController?.currentDestination是NavDestination对象
    //可以比较screen.route和currentRoute进行比较，都是String类型
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    val iconResource = if (currentRoute == screen.route) {
                        pic_selected[screen.route] ?: R.mipmap.sleep
                    } else {
                        pic_unselected[screen.route] ?: R.mipmap.sleep
                    }
                    Image(
                        painter = painterResource(id = iconResource),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                //这里的标签没有在Screen中定义，需要在Screen类中定义
                label = { Text(text = screen.route) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Home Screen")
    }
}

@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            IndeterminateCircularIndicator()
            LinearDeterminateIndicator()
        }

    }
}

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings Screen")
    }
}

sealed class Screen(val route: String, val resourceId: Int, val icon: ImageVector,selectedIcon :Int ,unselectedIcon :Int) {
    object Health : Screen("health", R.string.health_c, Icons.Default.Home,R.mipmap.health_orange,R.mipmap.health_gray)
    object Move : Screen("move", R.string.move_c, Icons.Default.Search,R.mipmap.move_orange,R.mipmap.move_gray)
    object Service : Screen("service", R.string.service_c, Icons.Default.Person,R.mipmap.device_orange,R.mipmap.device_gray)
    object Mine : Screen("mine", R.string.mine_c, Icons.Default.Settings,R.mipmap.mine_orange,R.mipmap.mine_gray)

    //health的子页面
    object CaloriesPage : Screen("CaloriesPage", R.string.mine_c, Icons.Default.Settings,R.mipmap.mine_orange,R.mipmap.mine_gray)
    object CaloriesPageDateView : Screen("CaloriesPageDateView", R.string.mine_c, Icons.Default.Settings,R.mipmap.mine_orange,R.mipmap.mine_gray)
    //object Vitality : Screen("mine", R.string.mine_c, Icons.Default.Settings,R.mipmap.mine_orange,R.mipmap.mine_gray)
}




@Preview
@Composable
fun DefaultPreview() {
    MainApp()
}


//
//@Composable
//fun BottomNavigationBar(navController: NavHostController) {
//    val items = listOf(
//        Screen.Health,
//        Screen.Move,
//        Screen.Service,
//        Screen.Mine
//    )


//map的使用不对，map的键是Screen类型，但是调用map内容的时候是String类型
//    val pics_Selected = mapOf(
//        Screen.Health to R.mipmap.health_orange,
//        Screen.Move to R.mipmap.move_orange,
//        Screen.Service to R.mipmap.device_orange,
//        Screen.Mine to R.mipmap.mine_orange
//    )
//    val pics_unselected = mapOf(
//        Screen.Health to R.mipmap.health_gray,
//        Screen.Move to R.mipmap.move_gray,
//        Screen.Service to R.mipmap.device_gray,
//        Screen.Mine to R.mipmap.mine_gray
//    )
//
//    val pic_unselected = mapOf(
//        "health" to R.mipmap.health_gray,
//        "move" to  R.mipmap.move_gray,
//        "service" to R.mipmap.device_gray,
//        "mine" to R.mipmap.mine_gray
//    )
//
//    val pic_selected = mapOf(
//        "health"  to R.mipmap.health_orange,
//        "move" to R.mipmap.move_orange,
//        "service" to R.mipmap.device_orange,
//        "mine"  to R.mipmap.mine_orange
//    )
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    BottomNavigation(
//        backgroundColor = Color.White,
//        elevation = 10.dp
//    ) {
//        items.forEach { screen ->
//            BottomNavigationItem(
//                icon = {
//      color = Color.  //不同类型的对象不能进行比较screen是一个Screen对象和navController?.currentDestination是NavDestination对象
//可以比较screen.route和currentRoute进行比较，都是String类型
////                    val iconResoource = if(screen == navController?.currentDestination){
//////                        Icons.Filled.Home
////                        pic_selected[screen]
////                    }else {
////                       pic_unselected[screen]
////                    }
//                    val iconResource = if (screen == navController?.currentDestination) {
//                        pic_selected[screen.route] ?: R.mipmap.sleep
//                    }
//                    else {
//                        pic_unselected[screen.route] ?: R.mipmap.sleep
//                    }
//
//                    Image(painter = painterResource(id = iconResource), contentDescription = null, modifier = Modifier.size(24.dp))
//                       },
//
//
////                icon = {
////                    Icon(
////                        imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
////                        contentDescription = screen.route
////                    )
////                },
//这里的标签没有在Screen中定义，需要在Screen类中定义
//                label = { Text(stringResource(screen.resourceId)) },
//                selected = currentRoute == screen.route,
//                onClick = {
//                    navController.navigate(screen.route) {
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
//                    }
//                }
//            )
//        }
//    }
//}
