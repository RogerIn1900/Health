package com.example.health.Apartment

//// 1. 数据模型层 (Data Layer)
//data class User(
//    val no: String,
//    val name: String,
//    val gender: String,
//    val email: String,
//    val academy: String,
//    val major: String
//)
//
//// 2. 网络请求层 (Network Layer)
//interface ApiService {
//    @POST("/user/login")
//    suspend fun login(@Body loginDto: StudentLoginDTO): ResultStudentVo
//
//    @POST("/user/register")
//    suspend fun register(@Body studentDto: StudentDTO): ResultStudentVo
//}
//
//// 3. 仓储层 (Repository)
//class UserRepository(private val apiService: ApiService) {
//    suspend fun loginUser(loginDto: StudentLoginDTO): Result<StudentVo> {
//        return try {
//            val response = apiService.login(loginDto)
//            if (response.code == 1) {
//                Result.success(response.data!!)
//            } else {
//                Result.failure(Exception(response.msg))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    suspend fun registerUser(studentDto: StudentDTO): Result<StudentVo> {
//        return try {
//            val response = apiService.register(studentDto)
//            if (response.code == 1) {
//                Result.success(response.data!!)
//            } else {
//                Result.failure(Exception(response.msg))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//}
//
//// 4. ViewModel层 (ViewModel)
//class AuthViewModel(private val repository: UserRepository) : ViewModel() {
//    private val _authState = mutableStateOf<AuthState>(AuthState.Unauthenticated)
//    val authState: State<AuthState> = _authState
//
//    fun login(no: String, email: String, password: String) {
//        viewModelScope.launch {
//            val result = repository.loginUser(StudentLoginDTO(no, email, password))
//            when (result) {
//                is Result.Success -> {
//                    _authState.value = AuthState.Authenticated(result.data)
//                    saveToken(result.data.no) // 保存Token
//                }
//                is Result.Failure -> {
//                    _authState.value = AuthState.Error(result.exception.message ?: "Unknown error")
//                }
//            }
//        }
//    }
//
//    fun register(name: String, no: String, gender: String, email: String, academy: String, major: String) {
//        viewModelScope.launch {
//            val student = StudentDTO(no, name, gender, email, academy, major)
//            val result = repository.registerUser(student)
//            when (result) {
//                is Result.Success -> {
//                    _authState.value = AuthState.RegisterSuccess
//                }
//                is Result.Failure -> {
//                    _authState.value = AuthState.Error(result.exception.message ?: "Registration failed")
//                }
//            }
//        }
//    }
//
//    private fun saveToken(token: String) {
//        // 使用SharedPreferences保存Token
//        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
//            putString("USER_TOKEN", token)
//            apply()
//        }
//    }
//}
//
//// 5. 状态管理 (State Management)
//sealed class AuthState {
//    object Unauthenticated : AuthState()
//    object Authenticated(val user: StudentVo) : AuthState()
//    object RegisterSuccess : AuthState()
//    data class Error(val message: String) : AuthState()
//}
//
//// 6. 页面实现 (Compose UI)
//@Composable
//fun MyApp() {
//    val navController = rememberNavController()
//    val authViewModel = viewModel<AuthViewModel>()
//
//    NavHost(navController, startDestination = if (isAuthenticated()) "main" else "auth") {
//        composable("auth") {
//            AuthScreen(navController, authViewModel)
//        }
//        composable("main") {
//            MainScreen(navController)
//        }
//        composable("profile") {
//            ProfileScreen()
//        }
//    }
//}
//
//@Composable
//fun AuthScreen(navController: NavController, authViewModel: AuthViewModel) {
//    var selectedTab by remember { mutableStateOf(AuthTab.Login) }
//
//    Column {
//        TabRow(selectedTabIndex = selectedTab.ordinal) {
//            AuthTab.values().forEachIndexed { index, tab ->
//                Tab(
//                    selected = selectedTab == tab,
//                    onClick = { selectedTab = tab },
//                    text = { Text(tab.title) }
//                )
//            }
//        }
//
//        when (selectedTab) {
//            AuthTab.Login -> LoginScreen(authViewModel)
//            AuthTab.Register -> RegisterScreen(authViewModel)
//        }
//    }
//}
//
//@Composable
//fun LoginScreen(authViewModel: AuthViewModel) {
//    val email = remember { mutableStateOf("") }
//    val password = remember { mutableStateOf("") }
//
//    Column {
//        OutlinedTextField(
//            value = email.value,
//            onValueChange = { email.value = it },
//            label = { Text("Email") }
//        )
//        OutlinedTextField(
//            value = password.value,
//            onValueChange = { password.value = it },
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation()
//        )
//        Button(onClick = {
//            authViewModel.login(
//                no = "123456", // 示例学号（实际应从其他途径获取）
//                email = email.value,
//                password = password.value
//            )
//        }) {
//            Text("Login")
//        }
//    }
//}
//
//@Composable
//fun RegisterScreen() {
//    val name = remember { mutableStateOf("") }
//    val no = remember { mutableStateOf("") }
//    val gender = remember { mutableStateOf("男") }
//    val email = remember { mutableStateOf("") }
//    val academy = remember { mutableStateOf("") }
//    val major = remember { mutableStateOf("") }
//
//    Column {
//        OutlinedTextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") })
//        OutlinedTextField(value = no.value, onValueChange = { no.value = it }, label = { Text("Student No") })
//        // 其他字段类似...
//        Button(onClick = {
//            authViewModel.register(
//                name = name.value,
//                no = no.value,
//                gender = gender.value,
//                email = email.value,
//                academy = academy.value,
//                major = major.value
//            )
//        }) {
//            Text("Register")
//        }
//    }
//}
//
//@Composable
//fun ProfileScreen() {
//    val user = getUserFromToken() // 从Token解析用户信息
//    Column {
//        Text(text = "Welcome, ${user.name}!")
//        Text(text = "Student No: ${user.no}")
//        Text(text = "Email: ${user.email}")
//        // 其他信息展示...
//        Button(onClick = { /* 退出登录逻辑 */ }) {
//            Text("Logout")
//        }
//    }
//}
//
//// 7. 导航辅助函数
//fun isAuthenticated(): Boolean {
//    return getToken() != null
//}
//
//fun getToken(): String? {
//    return PreferenceManager.getDefaultSharedPreferences(context)
//        .getString("USER_TOKEN", null)
//}
//
//// 8. 枚举类定义
//enum class AuthTab(val title: String) {
//    Login("Login"),
//    Register("Register")
//}

