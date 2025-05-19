package com.example.health.Apartment.Client

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import androidx.compose.runtime.State

// 定义公告数据类（根据接口返回结构调整）
data class Notice(
    val id: Int,
    val title: String,
    val content: String,
    val createTime: String
)

// 定义公告接口（需与后端实际接口匹配）
interface ApiService {
    @GET("notices")
    suspend fun getNotices(): List<Notice>
}

// Retrofit 工具类（单例模式）
object RetrofitClient {
    private const val BASE_URL = "https://your-backend-api.com/"  // 替换为你的实际接口地址

    // 创建 OkHttpClient（添加日志拦截器）
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY  // 调试时开启详细日志
        })
        .build()

    // 创建 Retrofit 实例
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 获取公告接口服务
    val api: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

// 在 ViewModel 中的使用示例（需配合协程）
class NoticeViewModel : ViewModel() {
    private val _notices = mutableStateOf<List<Notice>>(emptyList())
    val notices: State<List<Notice>> = _notices

    init {
        fetchNotices()
    }

    private fun fetchNotices() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.getNotices()
                _notices.value = response
            } catch (e: Exception) {
                Log.e("NoticeViewModel", "Error fetching notices: ${e.message}")
            }
        }
    }
}