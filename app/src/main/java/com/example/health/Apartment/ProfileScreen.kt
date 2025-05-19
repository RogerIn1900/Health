package com.example.health.Apartment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.health.ui.theme.DormitoryTheme
import com.example.health.ui.theme.md_theme_dark_primary

@Composable
fun ProfileScreen() {
    // 假数据
    val student = remember {
        mutableStateOf(
            mapOf(
                "学号" to "12345656",
                "姓名" to "张三",
                "性别" to "男",
                "邮箱" to "123456@qq.com",
                "学院" to "计算机学院",
                "专业" to "软件工程"
            )
        )
    }
    Card {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text("个人信息")
            Spacer(modifier = Modifier.height(24.dp))
            student.value.forEach { (k, v) ->
                Text("$k：$v")
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { }) {
                Text("返回")
            }
        }
    }
}

@Composable
fun DormitoryInfoScreen() {
    // 假数据
    val dormInfo = mapOf(
        "宿舍区" to "A区",
        "楼号" to "1",
        "宿舍号" to "101",
        "床位数" to "4"
    )
    Card{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text("宿舍信息", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(24.dp))
            dormInfo.forEach { (k, v) ->
                Text("$k：$v", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { }) {
                Text("返回")
            }
        }
    }
}

@Composable
fun MaintenanceListScreen() {
    // 假数据
    val records = listOf(
        "2024-05-01 水龙头漏水（处理中）",
        "2024-04-20 灯泡坏了（已完成）"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text("我的维修记录", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        records.forEach {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {  }) {
            Text("返回")
        }
    }
}

@Composable
fun MaintenanceApplyScreen() {
    var dorId by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var urgency by remember { mutableStateOf("1") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text("提交维修申请", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = dorId,
            onValueChange = { dorId = it },
            label = { Text("宿舍ID") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("维修描述") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = urgency,
            onValueChange = { urgency = it },
            label = { Text("紧急程度（1-低，2-中，3-高）") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("提交")
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("宿舍管理系统", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) { Text("个人信息") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) { Text("通知公告") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) { Text("提交维修申请") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) { Text("我的维修记录") }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) { Text("宿舍信息") }
    }
}

@Composable
fun RegisterScreen() {
    var no by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("学生注册", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = no,
            onValueChange = { no = it },
            label = { Text("学号") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("邮箱") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("注册")
        }
        TextButton(onClick = { }) {
            Text("返回登录")
        }
    }
}

@Composable
fun LoginScreen() {
    var no by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    DormitoryTheme(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("学生登录", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = no,
                onValueChange = { no = it },
                label = { Text("学号") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("密码") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {  },
                modifier = Modifier.run {
                    fillMaxWidth()
                }
                    .background(md_theme_dark_primary)
            ) {
                Text("登录")
            }
            TextButton(onClick = {  },
                modifier = Modifier.background(md_theme_dark_primary)

            ) {
                Text("没有账号？注册")
            }
        }
    }

}


@Preview
@Composable
fun preview(){
    ProfileScreen()
}
