package com.example.health.Myhealth.MidActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.ImageFormat
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.health.MinePage.LoginScreen
import com.example.health.R
import com.example.health.TopDesign.QRType.QRType
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.BinaryBitmap
import com.google.zxing.DecodeHintType
import com.google.zxing.MultiFormatReader
import com.google.zxing.common.HybridBinarizer
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
    }
}


// MainActivity.kt
@SuppressLint("RememberReturnType")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRCodeScannerActivity() {
    val context = LocalContext.current
    var scanResult by remember { mutableStateOf<Pair<String, QRType>?>(null) }
    var isScanning by remember { mutableStateOf(false) }

    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)
    val callPermission = rememberPermissionState(android.Manifest.permission.CALL_PHONE)
    val locationPermission = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {
        if (!cameraPermission.status.isGranted) {
            cameraPermission.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("二维码扫描") })
        }
    ) {
        if (isScanning) {
            CameraPreviewScreen(
                onScanResult = { result ->
                    scanResult = result
                    isScanning = false
                },
                onBack = { isScanning = false }
            )
        } else {
            ScanButton(onClick = { isScanning = true })
        }

        scanResult?.let { (content, type) ->
            ResultOverlay(content, type) {
                when (type) {
                    QRType.URL -> openWeb(context, content)
                    QRType.TEL -> callNumber(context, content)
                    QRType.LOCATION -> showMap(context, content)
                    QRType.TEXT -> showDialog(context, content)
                }
            }
        }
    }
}

// 扫描界面
@Composable
fun CameraPreviewScreen(
    onScanResult: (Pair<String, QRType>) -> Unit,
    onBack: () -> Unit
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(LocalContext.current)
    val preview = Preview.Builder().build().also {
        it.setSurfaceProvider(findSurfaceProvider())
    }

    val imageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()
        .also {
            it.setAnalyzer(cameraExecutor) { imageProxy ->
                val qrCode = QRCodeAnalyzer().analyze(imageProxy)
                onScanResult(qrCode to determineQRType(qrCode))
                imageProxy.close()
            }
        }

    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    LaunchedEffect(Unit) {
        cameraProviderFuture.get().bindToLifecycle(
            lifecycleOwner = LocalLifecycleOwner.current,
            cameraSelector = cameraSelector,
            preview = preview,
            imageAnalysis = imageAnalysis
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraView(modifier = Modifier.fillMaxSize())
        BackButton(onClick = onBack)
        ScanAnimation()
    }
}

// 二维码分析器
class QRCodeAnalyzer {
    private val reader = MultiFormatReader().apply {
        setHints(mapOf(
            DecodeHintType.POSSIBLE_FORMATS to listOf(QR_CODE)
        ))
    }

    @androidx.annotation.OptIn(ExperimentalGetImage::class)
    fun analyze(imageProxy: ImageProxy): String {
        val mediaImage = imageProxy.image
        if (mediaImage != null && mediaImage.format == ImageFormat.YUV_420_888) {
            val rotation = imageProxy.imageInfo.rotationDegrees
            val source = getLuminanceSource(mediaImage, rotation)
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
            return reader.decode(binaryBitmap).text
        }
        throw IllegalStateException("无法解析图像")
    }
}

// 结果处理扩展
fun determineQRType(content: String): QRType {
    return when {
        content.startsWith("http") -> QRType.URL
        content.startsWith("tel:") -> QRType.TEL
        content.matches(Regex("^geo:[0-9.]+,[0-9.]+$")) -> QRType.LOCATION
        else -> QRType.TEXT
    }
}

// 结果展示组件
@Composable
fun ResultOverlay(content: String, type: QRType, onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    color = Color.Black.copy(alpha = 0.7f),
                    size = size
                )
            }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        ) {
            Icon(
                imageVector = when (type) {
                    QRType.URL -> Icons.Default.Person
                    QRType.TEL -> Icons.Default.Call
                    QRType.LOCATION -> Icons.Default.LocationOn
                    else -> Icons.Default.Info
                },
                contentDescription = "结果类型",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(16.dp))
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onDismiss) {
                Text("关闭")
            }
        }
    }
}

// 辅助函数
fun openWeb(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    context.startActivity(intent)
}

fun callNumber(context: Context, number: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
    context.startActivity(intent)
}

fun showMap(context: Context, location: String) {
    val uri = "geo:${location.removePrefix("geo:")}?q=${Uri.encode(location)}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    context.startActivity(intent)
}

fun showDialog(context: Context, text: String) {
    AlertDialog.Builder(context)
        .setTitle("扫描结果")
        .setMessage(text)
        .setPositiveButton("确定") {
            it.dismiss()
        }
        .show()
}


@Preview
@Composable
fun LoginScreenPreview(){

}