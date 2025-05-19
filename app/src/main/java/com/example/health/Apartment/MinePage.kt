package com.example.health.Apartment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.compose.example.Screen
import com.example.health.R
import com.example.health.ui.theme.DormitoryTheme

@Composable
fun MinePage(navController: NavHostController){
    DormitoryTheme {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp) // Add space between items
        ){
            item {
                MinePart1(navController)
                Spacer(modifier = Modifier.height(10.dp))
//                MinePart3()
                Spacer(modifier = Modifier.height(10.dp))
                MinePart8(navController)
            }
        }
    }
}

data class Auth(var name: String,var sex: String,var no: Int)



@Composable
fun MinePart1(navController:NavHostController) {
//    TODO("Mine information")
    var auth: Auth = Auth("果仁芝麻糊","男",12345678)
    val name:String =auth.name
    val sex:String = auth.sex
    val no: Int = auth.no
    var isLogin by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.clickable {
            if (!isLogin){
                navController.navigate(Screen.LoginScreen.route)
                isLogin = true
            }else{
                navController.navigate(Screen.RegisterScreen.route)
            }
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(24.dp)
        ){
            Image(
                painter = painterResource(id = R.mipmap.stand),
                contentDescription = "nothing",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column {
                Text(name, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Text(sex, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("学号:" + no.toString(), fontSize = 20.sp)


                }
            }
        }
    }
}



@Composable
fun MinePart3() {
//    TODO("Not yet implemented")
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
                .fillMaxWidth()

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

//// 在顶层定义路由常量
//object Routes {
//    const val STUDENT_INFO = "student_info"
//    const val ADMIN_INFO = "admin_info"
//    const val NOTICES = "notices"//
//    const val REPAIR = "repair"//
//    const val DORM = "dorm"//
//    const val HYGIENE_CHECK = "hygiene_check"
//}
//
data class MineData(
    val route: Screen,  // 新增路由字段[7](@ref)
    val imagePath: Int,
    val name: String,
    val version: String = ""
)


@Composable
fun MinePart8(navController:NavHostController) {
//    TODO("Not yet implemented")
    val mineDatas = listOf(
        MineData(Screen.STUDENT_INFO,R.drawable.baseline_account_circle_20,"学生个人信息"),
        MineData(Screen.ADMIN_INFO,R.drawable.baseline_school_24,"管理员个人信息"),
        MineData(Screen.NoticeBoardScreen,R.drawable.baseline_article_24,"通知"),
        MineData(Screen.Rest02,R.drawable.baseline_build_24,"报修"),
        MineData(Screen.Dormitory,R.drawable.baseline_home_24,"宿舍"),
        MineData(Screen.HYGIENE_CHECK,R.drawable.baseline_edit_note_24,"卫生检查"),
    )

    val mineDatas2 = listOf(
        MineData(Screen.DormitoryInfoScreen,R.drawable.baseline_edit_note_24,"DormitoryInfoScreen"),
        MineData(Screen.MaintenanceListScreen,R.drawable.baseline_edit_note_24,"MaintenanceListScreen"),
        MineData(Screen.MaintenanceApplyScreen,R.drawable.baseline_edit_note_24,"MaintenanceApplyScreen"),
        MineData(Screen.RegisterScreen,R.drawable.baseline_edit_note_24,"RegisterScreen"),
        MineData(Screen.LoginScreen,R.drawable.baseline_edit_note_24,"LoginScreen"),
        MineData(Screen.Rest01,R.drawable.baseline_edit_note_24,"Rest01"),
        MineData(Screen.Rest02,R.drawable.baseline_edit_note_24,"Rest02"),

        )

    Card {
        Column (
        ){
            for (mineData in mineDatas){
                MineCard(mineData,navController)
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Card {
        Column (
        ) {
            for (mineData in mineDatas2) {
                MineCard(mineData, navController)
            }
        }
    }
}

@Composable
fun MineCard(mineData: MineData, navController: NavHostController) {
    Row(
        modifier = Modifier.padding(8.dp)
            .clickable {
                navController.navigate(mineData.route.route){
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
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


@Preview
@Composable
fun PreviewMinePage() {
    DormitoryTheme {

        Column {
//        MinePart1()
//        MinePart2()
//            MinePage()
//        MinePart6()
        }
    }
}