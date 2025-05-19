package com.example.compose.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.health.R
import com.example.health.TopDesign.HomeTop
import com.example.health.isSystemDarkMode
import com.example.health.navMap
import com.example.health.ui.theme.DormitoryTheme
import com.example.health.ui.theme.darkBackgroundColor
import com.example.health.ui.theme.lightBackgroundColor

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
    val title: String = when (currentRoute) {
        Screen.Dormitory.route -> stringResource(id = R.string.dormitory)
        Screen.Rest02.route -> stringResource(id = R.string.maintenance)
        Screen.NoticeBoardScreen.route -> stringResource(id = R.string.NoticeBoardScreen)
        Screen.Mine.route -> stringResource(id = R.string.mine)

        Screen.STUDENT_INFO.route -> stringResource(id = R.string.student_info)
        Screen.ADMIN_INFO.route -> stringResource(id = R.string.admin_info)
        Screen.HYGIENE_CHECK.route -> stringResource(id = R.string.hygiene_check)
        else -> "Unknown"
    }
    val isBottomBarVisible = when (currentRoute) {
        Screen.Dormitory.route -> true
        Screen.Rest02.route -> true
        Screen.NoticeBoardScreen.route -> true
        Screen.Mine.route -> true
        else -> true
    }
    val isTopBarVisible = when (currentRoute) {
        Screen.Dormitory.route -> true
        Screen.Rest02.route -> true
        Screen.NoticeBoardScreen.route -> true
        Screen.Mine.route -> true

        else -> true
    }


    DormitoryTheme {
        Scaffold(
            topBar = {
                when (isTopBarVisible) {
                    true -> {
                        HomeTop(title)
                    }
                    false -> {
//                        vitalityTop(navController)
                        HomeTop(title)
                    }
                }
            },
            bottomBar = {
                if (isBottomBarVisible == true) BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Dormitory.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                navMap(navController)
            }
        }
    }

}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Dormitory,
//        Screen.Health,
        Screen.Rest02,
        Screen.NoticeBoardScreen,
        Screen.Mine
    )

    //map的使用不对，map的键是Screen类型，但是调用map内容的时候是String类型
    val pic_unselected = mapOf(
        "宿舍" to R.drawable.baseline_home_black_24,
        "报修" to R.drawable.baseline_build_black_24,
        "公告" to R.drawable.baseline_article_black_24,
        "我的" to R.drawable.baseline_account_circle_black_24
    )

    val pic_selected = mapOf(
        "宿舍" to R.drawable.baseline_home_white_24,
        "报修" to R.drawable.baseline_build_24,
        "公告" to R.drawable.baseline_article_24,
        "我的" to R.drawable.baseline_account_circle_24
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val backgroundColor = if(isSystemDarkMode()){
        darkBackgroundColor
    }else{
        lightBackgroundColor
    }

    BottomNavigation(
        backgroundColor = backgroundColor,
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

sealed class Screen(
    val route: String,
    val resourceId: Int,
    val icon: ImageVector,
    selectedIcon: Int,
    unselectedIcon: Int
) {
    object Dormitory : Screen(
        "宿舍",
        R.string.dormitory,
        Icons.Default.Home,
        R.drawable.baseline_home_white_24,
        R.drawable.baseline_home_black_24
    )
    object Rest02 : Screen(
        "报修",
        R.string.maintenance,
        Icons.Default.Search,
        R.drawable.baseline_build_24,
        R.drawable.baseline_build_black_24
    )
    object NoticeBoardScreen : Screen(
        "公告",
        R.string.notice,
        Icons.Default.Person,
        R.drawable.baseline_article_24,
        R.drawable.baseline_article_black_24
    )
    object Mine : Screen(
        "我的",
        R.string.mine_c,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object STUDENT_INFO : Screen(
        "学生信息",
        R.string.student_info,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object ADMIN_INFO : Screen(
        "教师信息",
        R.string.admin_info,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object HYGIENE_CHECK : Screen(
        "报修",
        R.string.hygiene_check,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )


    object DormitoryInfoScreen : Screen(
        "宿舍信息",
        R.string.dormitory_info,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object MaintenanceListScreen : Screen(
        "报修列表",
        R.string.hygiene_check_list,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object MaintenanceApplyScreen : Screen(
        "报修申请",
        R.string.hygiene_check_apply,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object RegisterScreen : Screen(
        "注册",
        R.string.register,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object LoginScreen : Screen(
        "登陆",
        R.string.login,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
    object Rest01 : Screen(
        "剩余页面01",
        R.string.rest01,
        Icons.Default.Settings,
        R.drawable.baseline_account_circle_24,
        R.drawable.baseline_account_circle_black_24
    )
//    object Rest02 : Screen(
//        "剩余页面02",
//        R.string.rest02,
//        Icons.Default.Settings,
//        R.drawable.baseline_account_circle_24,
//        R.drawable.baseline_account_circle_black_24
//    )
}


@Preview
@Composable
fun DefaultPreview() {
    MainApp()
}

