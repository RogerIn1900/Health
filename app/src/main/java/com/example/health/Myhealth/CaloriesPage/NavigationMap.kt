package com.example.health.Myhealth.CaloriesPage

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.compose.example.Screen
import com.example.compose.example.SearchScreen
import com.example.health.DevicePage.DevicePage
import com.example.health.MinePage.MinePage
import com.example.health.Myhealth.MidActivity.MidActivity
import com.example.health.Myhealth.StepNumber.StepNumber
import com.example.health.Myhealth.Vitality.VitalityDates
import com.example.health.Myhealth.Vitality.VitalityIndex
import com.example.health.PostPic.ImageUploaderScreen

fun NavGraphBuilder.navMap(navController: NavController){

    composable(Screen.Move.route) { SearchScreen() }
    composable(Screen.Service.route) { DevicePage() }
    composable(Screen.Mine.route) { MinePage() }


    //Health页面的导航页
    composable(
        route = "Vitality",
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
    ) { VitalityIndex(navController) }
    // 1. 定义参数化路由
    // 1. 定义参数化路由
    composable(
        "detail_screen/{itemId}",
        arguments = listOf(navArgument("itemId") { type = NavType.IntType })
    ) { backStackEntry ->
        val itemId = backStackEntry.arguments?.getInt("itemId")
        VitalityIndex(navController)
    }

    //health页面内的子页面
    //卡路里单独页面
    composable(
        route = "CaloriesPage",
        enterTransition = {
            slideInHorizontally(animationSpec = tween(300)) { fullWidth ->
                fullWidth  // 从右侧滑入
            }
//                    + fadeIn(animationSpec = tween(300))
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
        CaloriesPage()
    }
    //步数页面
    composable(
        route = "StepNumber",
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
        StepNumber()
    }
    //中高强度运动页面
    composable(
        route = "MidActivity",
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
        MidActivity()
    }

    //子子视图
    //卡路里日视图
    composable(
        route = "CaloriesPageDateView",
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
        CaloriesPageDateView(navController)
    }

    //vitality页面
    //顶部日期跳转
    composable(
        route = "VitalityDates",
        enterTransition = {
            slideInHorizontally(animationSpec = tween(300)) { fullWidth ->
                fullWidth  // 从右侧滑入
            } + fadeIn(animationSpec= tween(300))
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
    ){
        VitalityDates(navController = navController)
    }
}

fun NavController.toMain(){
    navigate(""){
        popUpToId
    }
    navigate(""){
        popUpToId
    }
    navigate(""){
        popUpToId
    }
}
