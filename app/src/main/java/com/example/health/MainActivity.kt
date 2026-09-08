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
        setContent { com.example.health.feature.HealthRoot() }
    }
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
