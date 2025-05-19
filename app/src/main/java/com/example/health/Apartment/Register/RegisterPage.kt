//package com.example.health.Apartment.Register
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.test.espresso.core.internal.deps.dagger.Module
//import androidx.test.espresso.core.internal.deps.dagger.Provides
//import com.example.health.Apartment.Client.ApiService
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//@Composable
//fun Register(){
//    Column {
//        // 状态管理
//        var no by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//        var errorMessage by remember { mutableStateOf<String?>(null) }
//        var isLoading by remember { mutableStateOf(false) }
//        // 表单验证
//        val isFormValid = no.length == 12 && password.length >= 6
//// 在 NetworkModule 或 ViewModel 中
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://your-api-base-url.com/") // 确保 Base URL 正确
//            .addConverterFactory(GsonConverterFactory.create()) // 必须添加 Gson 转换器
//            .build()
//
//        val apiService = retrofit.create(DormitoryApiService::class.java) // 必须创建实例
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .verticalScroll(rememberScrollState()),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // 标题
//            Text(
//                text = "宿舍管理系统登录",
//                style = MaterialTheme.typography.headlineMedium,
//                modifier = Modifier.padding(bottom = 32.dp)
//            )
//
//            // 学号输入框
//            (if (no.length == 12) VisualTransformation.None else null)?.let {
//                TextField(
//                    value = no,
//                    onValueChange = { no = it },
//                    label = { Text("学号") },
//                    placeholder = { Text("请输入12位学号") },
//                    visualTransformation = it,
//                    isError = no.length != 12,
//                    supportingText = {
//                        if (no.length != 12) {
//                            Text("学号必须为12位数字", color = MaterialTheme.colorScheme.error)
//                        }
//                    },
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // 密码输入框
//            TextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("密码") },
//                placeholder = { Text("至少6位字符") },
//                visualTransformation = PasswordVisualTransformation(),
//                isError = password.length < 6,
//                supportingText = {
//                    if (password.length < 6) {
//                        Text("密码长度不足", color = MaterialTheme.colorScheme.error)
//                    }
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // 登录按钮
//            Button(
//                onClick = {
//                    isLoading = true
//                    viewModelScope.launch {
//                        try {
//                            val response = apiService.login(no, password)
//                            if (response.code == 200) {
//                                // 处理登录成功
//                            } else {
//                                errorMessage = response.message
//                            }
//                        } catch (e: Exception) {
//                            errorMessage = "网络错误，请重试"
//                        }
//                        isLoading = false
//                    }
//                },
//                enabled = isFormValid && !isLoading,
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = if (isFormValid) MaterialTheme.colorScheme.primary
//                    else MaterialTheme.colorScheme.surfaceVariant
//                )
//            ) {
//                if (isLoading) {
//                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
//                } else {
//                    Text(if (isFormValid) "立即登录" else "请完善信息")
//                }
//            }
//
//            // 错误提示
//            errorMessage?.let {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = it,
//                    color = MaterialTheme.colorScheme.error,
//                    style = MaterialTheme.typography.bodyMedium,
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                )
//            }
//        }
//    }
//}
//
//@Module
//@InstallIn(ViewModelComponent::class)
//object NetworkModule {
//    @Provides
//    fun provideApiService(retrofit: Retrofit): DormitoryApiService {
//        return retrofit.create(DormitoryApiService::class.java)
//    }
//}