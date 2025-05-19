package com.example.health

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.compose.example.Screen
import com.example.health.Apartment.DormitoryInfoScreen
import com.example.health.Apartment.DormitoryList
import com.example.health.Apartment.DormitoryMaintenanceScreen
import com.example.health.Apartment.LoginScreen
import com.example.health.Apartment.MaintenanceApplyScreen
import com.example.health.Apartment.MaintenanceListScreen
import com.example.health.Apartment.MinePage
import com.example.health.Apartment.NoticeBoardScreen
import com.example.health.Apartment.ProfileScreen
import com.example.health.Apartment.RegisterScreen

fun NavGraphBuilder.navMap(navController: NavHostController){
    //底部导航显示的页面
    composable(Screen.Dormitory.route) { DormitoryList() }
    composable(Screen.Rest02.route) { DormitoryMaintenanceScreen() }
    composable(Screen.NoticeBoardScreen.route) { NoticeBoardScreen() }
    composable(Screen.Mine.route) { MinePage(navController) }
    //剩余的主要页面
    composable(Screen.STUDENT_INFO.route) { ProfileScreen(navController) }
    composable(Screen.ADMIN_INFO.route) { ProfileScreen(navController) }
    composable(Screen.HYGIENE_CHECK.route) { NoticeBoardScreen() }
    //其他页面
    composable(Screen.DormitoryInfoScreen.route) { DormitoryInfoScreen() }
    composable(Screen.MaintenanceListScreen.route) { MaintenanceListScreen() }
    composable(Screen.MaintenanceApplyScreen.route) { MaintenanceApplyScreen() }
    composable(Screen.RegisterScreen.route) { RegisterScreen(navController) }
    composable(Screen.LoginScreen.route) { LoginScreen(navController) }
    composable(Screen.Rest01.route) { NoticeBoardScreen() }
    composable(Screen.Rest02.route) { DormitoryMaintenanceScreen() }

}
