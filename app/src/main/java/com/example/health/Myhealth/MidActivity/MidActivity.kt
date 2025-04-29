package com.example.health.Myhealth.MidActivity

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health.DevicePage.DevicePage
import com.example.health.DevicePage.PartMarket
import com.example.health.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun MidActivity() {
    Column {
        Image(
            painter = painterResource(id = R.mipmap.calorie),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
//        LoginScreen()
        DevicePage()

    }

}



// 1. 定义 UI 状态密封类
sealed class LoginState {
    object SignedOut : LoginState()          // 未登录状态
    object Loading : LoginState()           // 加载中状态
    data class Error(val message: String) : LoginState() // 错误状态（携带错误信息）
    object Success : LoginState()           // 登录成功状态
}

// 模拟认证数据仓库
class AuthRepository {
    // 模拟网络请求（实际开发中应使用 Retrofit 等库）
    suspend fun login(email: String, password: String) {
        delay(1000) // 模拟网络延迟
        if (email == "test@example.com" && password == "123456") {
            // 登录成功（无返回值）
            // 显示简短提示（2秒）
//            Toast.makeText(requireContext(), "Message", Toast.LENGTH_SHORT).show()
        } else {
            throw Exception("Invalid credentials") // 模拟错误
        }
    }
}

// 2. 创建 ViewModel
class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepository()// 假设的认证仓库
) : ViewModel() {

    // 使用 StateFlow 管理状态（也可以用 LiveData）
    private val _uiState = MutableStateFlow<LoginState>(LoginState.SignedOut)
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    // 处理登录事件
    fun onSignIn(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginState.Loading
            try {
                authRepository.login(email, password)
                _uiState.value = LoginState.Success
            } catch (e: Exception) {
                _uiState.value = LoginState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // 重置状态到未登录
    fun resetState() {
        _uiState.value = LoginState.SignedOut
    }
}

// 3. Compose UI 组件
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = LoginViewModel(),
    onLoginSuccess: () -> Unit = {}// 导航回调
) {
    val uiState by viewModel.uiState.collectAsState()

    // 根据状态处理副作用
    when (uiState) {
        is LoginState.Success -> {
            LaunchedEffect(Unit) {
                onLoginSuccess() // 导航到其他屏幕
                viewModel.resetState()
            }
        }
        else -> {}
    }

    LoginContent(
        uiState = uiState,
        onSignIn = { email, password ->
            viewModel.onSignIn(email, password)
        }
    )
}

@Composable
private fun LoginContent(
    uiState: LoginState,
    onSignIn: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 邮箱输入
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 密码输入
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }
            is LoginState.Error -> {
                // 显示错误提示
                Snackbar(
                    action = { /* 可选操作按钮 */ },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text((uiState as LoginState.Error).message)
                }
            }
            else -> {
                Button(
                    onClick = { onSignIn(email, password) },
                    enabled = uiState !is LoginState.Loading
                ) {
                    Text("Sign In")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview(){
//    LoginScreen()
}