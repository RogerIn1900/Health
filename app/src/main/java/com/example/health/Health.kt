package com.example.compose.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.health.IndeterminateCircularIndicator
import com.example.health.LinearDeterminateIndicator
import com.example.health.MinePage
import com.example.health.MyHealth
import com.example.health.R

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
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val title :String = when (currentRoute){
        Screen.Health.route -> stringResource(id = R.string.health)
        Screen.Move.route  ->  stringResource(id = R.string.move)
        Screen.Service.route  ->  stringResource(id = R.string.service)
        Screen.Mine.route  ->  stringResource(id = R.string.mine)
        else -> "Unknown"
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text(title.toString()) }) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Health.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Health.route) { MyHealth() }
            composable(Screen.Move.route) { SearchScreen() }
            composable(Screen.Service.route) { ProfileScreen() }
            composable(Screen.Mine.route) { MinePage() }
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
    object Health : Screen("health", R.string.health, Icons.Default.Home,R.mipmap.health_orange,R.mipmap.health_gray)
    object Move : Screen("move", R.string.move, Icons.Default.Search,R.mipmap.move_orange,R.mipmap.move_gray)
    object Service : Screen("service", R.string.service, Icons.Default.Person,R.mipmap.device_orange,R.mipmap.device_gray)
    object Mine : Screen("mine", R.string.mine, Icons.Default.Settings,R.mipmap.mine_orange,R.mipmap.mine_gray)
}




@Preview
@Composable
fun DefaultPreview() {
    MainApp()
}

