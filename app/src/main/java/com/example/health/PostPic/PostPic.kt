package com.example.health.PostPic


import android.content.Context
import androidx.compose.runtime.Composable
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.health.ApiService.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
@Composable
fun ImageUploaderScreen() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var uploadStatus by remember { mutableStateOf("") }
    val context = LocalContext.current // 获取当前上下文
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text("选择图片")
        }

        Spacer(modifier = Modifier.height(16.dp))

        imageUri?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            imageUri?.let { uri ->
                uploadImage(uri, context) { status -> // 传递上下文
                    uploadStatus = status
                }
            }
        }) {
            Text("上传图片")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(uploadStatus)
    }
}
fun getRealPathFromURI(context: Context, uri: Uri): String? {
    var cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            return it.getString(columnIndex)
        }
    }
    return null
}fun uploadImage(uri: Uri, context: Context, onStatusChange: (String) -> Unit) {
    val filePath = getRealPathFromURI(context, uri)
    if (filePath != null) {
        val file = File(filePath)

        // 创建 RequestBody
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)

        // 创建 MultipartBody.Part
        val body = MultipartBody.Part.createFormData("images", file.name, requestFile)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.70.140.3:8080/") // 替换为您的 API 基础 URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.uploadImage(body).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    onStatusChange("上传成功")
                } else {
                    onStatusChange("上传失败: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onStatusChange("上传错误: ${t.message}")
            }
        })
    } else {
        onStatusChange("无法获取文件路径")
    }
}