package com.example.health.feature.scan

import android.Manifest
import android.content.pm.PackageManager
import android.os.SystemClock
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

@Composable
fun ScannerScreen(onResult: (ScanResult) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnResult by rememberUpdatedState(onResult)
    var granted by remember { mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted = it }
    var error by remember { mutableStateOf<String?>(null) }
    if (!granted) {
        Column(Modifier.padding(16.dp)) {
            Text("扫描二维码需要相机权限；拒绝后仍可使用健康记录。")
            Button(onClick = { permission.launch(Manifest.permission.CAMERA) }) { Text("允许相机") }
        }
        return
    }
    val previewView = remember(context) { PreviewView(context) }
    DisposableEffect(lifecycleOwner, previewView) {
        val active = AtomicBoolean(true)
        val worker = Executors.newSingleThreadExecutor()
        val main = ContextCompat.getMainExecutor(context)
        val preview = Preview.Builder().build().apply { setSurfaceProvider(previewView.surfaceProvider) }
        val analysis = ImageAnalysis.Builder().setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()
        analysis.setAnalyzer(worker, QrFrameAnalyzer(ScanGate(SystemClock::elapsedRealtime)) { result ->
            main.execute { if (active.get()) currentOnResult(result) }
        })
        var provider: ProcessCameraProvider? = null
        val future = ProcessCameraProvider.getInstance(context)
        future.addListener({
            if (active.get()) {
                try {
                    provider = future.get()
                    provider!!.bindToLifecycle(lifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA, preview, analysis)
                } catch (failure: Exception) {
                    error = "相机无法启动，请检查权限或相机是否被占用"
                }
            }
        }, main)
        onDispose {
            active.set(false)
            analysis.clearAnalyzer()
            provider?.unbind(preview, analysis)
            worker.shutdown()
        }
    }
    Column {
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxWidth().height(280.dp))
    }
}
