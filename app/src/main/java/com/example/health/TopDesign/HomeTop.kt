package com.example.health.TopDesign


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog

import android.Manifest
import android.graphics.ImageFormat
import android.util.Size
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors




@Composable
fun HomeTop(title: String){
    TopAppBar(title = { Text(title.toString(),modifier = Modifier.size(36.dp))})
}

@Composable
fun DropdownMenuButton() {
    var showDialog by remember { mutableStateOf(false) }
//扫一扫
    var isScanning by remember { mutableStateOf(false) }
    var scanResult by remember { mutableStateOf<String?>(null) }


    IconButton(onClick = { showDialog = true }) {
        Icon(Icons.Default.AddCircle,
            contentDescription = "更多选项",
            modifier = Modifier.size(36.dp)
        )
    }

    // 显示对话框
    if (showDialog) {
        Dialog(onDismissRequest = { },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Unspecified,
                        shape = RoundedCornerShape(30.dp)
                    )
            ){
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .align(TopEnd)
                        .wrapContentSize()
                        .padding(20.dp)
                        .background(
                            color = Color.Unspecified,
                            shape = RoundedCornerShape(30.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Button(
                            colors = ButtonDefaults.buttonColors(Color.White),
                            onClick = {
                                showDialog = false
                            },
                            modifier = Modifier
                                .width(200.dp)

                            ,
                        ) {
                            Text("添加设备")
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.White),
                            onClick = {
                                showDialog = false
                                isScanning = true
                            },
                            modifier = Modifier
                                .width(200.dp)
                                .background(Color.White)

                        ) {
                            Text("扫一扫")
                        }
                    }
                }
            }
        }
    }


    if (scanResult != null) {
        Text("Scanned QR Code: $scanResult")
    }

    if (isScanning) {
        ScanQRCodeScreen(
            isScanning = isScanning,
            onScanResult = { qrCode ->
                isScanning = false
                scanResult = qrCode
            }
        )
    }
}


//：扫一扫功能
@Composable
fun ScanQRCodeScreen(
    isScanning: Boolean, // 控制是否显示扫一扫界面
    onScanResult: (String) -> Unit // 扫描结果的回调
) {
    if (isScanning) {
        CameraPermission(onScanResult)
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermission(onScanResult: (String) -> Unit) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    if (permissionState.status.isGranted) {
        CameraScanner(onScanResult)
    } else {
        LaunchedEffect(Unit) {
            permissionState.launchPermissionRequest()
        }
    }
}


@Composable
fun CameraScanner(onScanResult: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val previewView = PreviewView(ctx)
            val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

            cameraProviderFuture.addListener({
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(Size(640, 480))
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, QRCodeAnalyzer { qrCode ->
                            // 处理扫描到的二维码信息
//                            println("Scanned QR Code: $qrCode")
                            onScanResult(qrCode) // 将扫描结果传递给回调
                        })
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        }
    )
}


class QRCodeAnalyzer(private val onQrCodeScanned: (String) -> Unit) : ImageAnalysis.Analyzer {
    private val reader = MultiFormatReader()

    @ExperimentalGetImage
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null && image.format == ImageFormat.YUV_420_888) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val source = getLuminanceSource(mediaImage, rotationDegrees)
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))

            try {
                val result = reader.decode(binaryBitmap)
                onQrCodeScanned(result.text)
            } catch (e: Exception) {
                // 解码失败
            } finally {
                image.close()
            }
        }
    }

    private fun getLuminanceSource(
        image: android.media.Image,
        rotationDegrees: Int
    ): PlanarYUVLuminanceSource {
        val yBuffer = image.planes[0].buffer
        val uBuffer = image.planes[1].buffer
        val vBuffer = image.planes[2].buffer
        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)
        // U and V are swapped
        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val width = image.width
        val height = image.height

        var rotatedWidth = width
        var rotatedHeight = height
        var data = nv21
        if (rotationDegrees == Surface.ROTATION_90 || rotationDegrees == Surface.ROTATION_270) {
            rotatedWidth = height
            rotatedHeight = width
            data = ByteArray(nv21.size)
            for (y in 0 until height) {
                for (x in 0 until width) {
                    if (rotationDegrees == Surface.ROTATION_90) {
                        data[x * height + height - y - 1] = nv21[x + y * width]
                    } else if (rotationDegrees == Surface.ROTATION_270) {
                        data[(width - x - 1) * height + y] = nv21[x + y * width]
                    }
                }
            }
        }

        return PlanarYUVLuminanceSource(
            data,
            rotatedWidth,
            rotatedHeight,
            0,
            0,
            rotatedWidth,
            rotatedHeight,
            false
        )
    }
}


@androidx.compose.ui.tooling.preview.Preview
@Composable
fun DefaultPreview() {
//    CameraPermission()
}
