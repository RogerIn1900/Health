package com.example.health.Myhealth

import android.graphics.Picture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.example.Screen
import com.example.health.MainViewModel.MainViewModel
import com.example.health.R

@Composable
fun CaloriesPage() {
    Column {
        //TODO:日、周、月视图切换
        Row (
            modifier = Modifier.padding(10.dp)
        ) {
            navBar()
        }

        //TODO:对应视图的图表
        Row {

        }

        //TODO:对应视图的概览
        Card {
            Column {
                Row {
                    //TODO:图标
                    //TODO:今日概览
                }
                //TODO:分界线
                Column {
                    //TODO:消耗
                    //TODO：今日消耗
                }
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Card {
            Row {
                Text("了解卡路里")
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.mipmap.calorie),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun navBar() {
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
    val isBottomBarVisible = when (currentRoute){
        Screen.Health.route -> true
        Screen.Move.route  -> true
        Screen.Service.route  ->  true
        Screen.Mine.route  ->  true
        else -> false
    }
    val isTopBarVisible = when (currentRoute){
        Screen.Health.route -> true
        Screen.Move.route  -> true
        Screen.Service.route  ->  true
        Screen.Mine.route  ->  true
        else -> false
    }
}

@Preview
@Composable
fun CaloriesPagePreview(){
    CaloriesPage()
}