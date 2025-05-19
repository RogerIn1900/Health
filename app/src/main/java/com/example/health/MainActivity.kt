package com.example.health

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import com.example.compose.example.MainApp
import com.example.health.ui.theme.DormitoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        // 关键设置：让内容延伸到系统栏后面
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            DormitoryTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                MainApp()
            }
        }
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show()
    }
//
//    override fun onStart() {
//        super.onStart()
//        Toast.makeText(this,"onStart",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()
//    }
//
//
//
//
//    override fun onPause() {
//        super.onPause()
//        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Toast.makeText(this,"onStop",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onDestroy(){
//        super.onDestroy()
//        Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show()
//
//    }
}

//获取主题颜色
@Composable
fun isSystemDarkMode(): Boolean {
    val configuration = LocalContext.current.resources.configuration
    return when (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO -> false
        else -> false // 默认浅色
    }
}

@Composable
fun isSystemLightMode(): Boolean{
    val configuration = LocalContext.current.resources.configuration
    return when (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
        Configuration.UI_MODE_NIGHT_YES -> false
        Configuration.UI_MODE_NIGHT_NO -> true
        else -> false
    }
}
