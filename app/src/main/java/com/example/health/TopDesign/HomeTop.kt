package com.example.health.TopDesign

import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Surface
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
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.ImageFormat
import android.graphics.drawable.Icon
import android.net.Uri
import android.util.Log
import android.util.Size
import android.view.Surface
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.compose.rememberNavController
import com.example.health.R
import com.example.health.isSystemDarkMode
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import org.chromium.base.Callback
import java.io.IOException
import java.util.UUID
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Composable
fun HomeTop(title: String) {
//
//    val url =  "http://Practicemakesperfect.cambridge.org"
//    val navController = rememberNavController()
//    val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
//    val context = LocalContext.current
//    context.startActivity(intent)



    rememberNavController()
    TopAppBar(
        title = { Text(title.toString()) },
        actions = {
            DropdownMenuButton()
        }
    )
}

//右上角的展开按钮
@Composable
fun DropdownMenuButton(isLightTheme : Boolean = true) {
    var showDialog by remember { mutableStateOf(false) }
    //扫一扫
    var isScanning by remember { mutableStateOf(false) }
    var scanResult by remember { mutableStateOf<String?>(null) }
    //蓝牙
    var isBluetooth by remember { mutableStateOf(false) }

    IconButton(onClick = { showDialog = true }) {
        if (!isSystemDarkMode()){
            Image(
                painter = painterResource(R.drawable.baseline_add_circle_outline_black_24),
                contentDescription = "更多选项",
                modifier = Modifier.size(36.dp)
            )
        }else{
            Image(
                painter = painterResource(R.drawable.baseline_add_circle_outline_white_24),
                contentDescription = "更多选项",
                modifier = Modifier.size(36.dp)
            )
        }

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
                        .width(200.dp)
                        .height(100.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
//                            .background(Color.White),
//                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //蓝牙添加设备
                        TextButton(
//                            colors = ButtonDefaults.buttonColors(Color.White),
                            onClick = {
                                showDialog = false
                                isBluetooth = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text("添加设备")
                        }
                        //使用相机扫一扫
                        TextButton(
                            onClick = {
                                showDialog = false
                                isScanning = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ){
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

    var onBackPressed:() -> Unit = {isScanning = false}
    var onBackPressed2:() -> Unit = {isBluetooth = false}
    if (isScanning) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){

                ScanQRCodeScreen(
                    isScanning = isScanning,
                    onScanResult = { qrCode ->
                        isScanning = false
                        scanResult = qrCode
                    }
                )

                // 返回按钮
                IconButton(
                    onClick = onBackPressed,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                // 白色方框和扫描线
                if (isScanning) {
                    val transition = rememberInfiniteTransition()
                    val scanLinePosition by transition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart
                        )
                    )

                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val boxWidth = size.width * 0.7f
                        val boxHeight = boxWidth
                        val boxLeft = (size.width - boxWidth) / 2
                        val boxTop = (size.height - boxHeight) / 2

                        // 绘制白色方框
                        drawRect(
                            color = Color.White,
                            topLeft = Offset(boxLeft, boxTop),
                            size = androidx.compose.ui.geometry.Size(boxWidth, boxHeight),
                            style = Stroke(width = 2f)
                        )

                        // 绘制扫描线
                        val scanLineY = boxTop + boxHeight * scanLinePosition
                        drawLine(
                            color = Color.White,
                            start = Offset(boxLeft, scanLineY),
                            end = Offset(boxLeft + boxWidth, scanLineY),
                            strokeWidth = 2f,
                            cap = StrokeCap.Round
                        )
                    }
                }
            }
        }
    }


    //蓝牙
    if (isBluetooth) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                BluetoothScreen(modifier = Modifier
                    .align(Center)
                )
                // 返回按钮
                IconButton(
                    onClick = onBackPressed2,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }


            }
        }
    }

}

//蓝牙功能：
@Composable
fun BluetoothScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bluetoothAdapter = remember { BluetoothAdapter.getDefaultAdapter() }
    val devices = remember { mutableStateListOf<BluetoothDevice>() }
    val isScanning = remember { mutableStateOf(false) }
    val connectedDevice = remember { mutableStateOf<BluetoothDevice?>(null) }
    val isConnected = remember { mutableStateOf(false) }

    // 扫描设备
    val scanCallback = remember {
        object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                super.onScanResult(callbackType, result)
                val device = result.device
                if (!devices.contains(device)) {
                    devices.add(device)
                }
                devices.add(device)

            }
        }
    }

    fun startScan() {
        isScanning.value = true
        devices.clear()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_SCAN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        bluetoothAdapter?.bluetoothLeScanner?.startScan(scanCallback)
    }

    // 权限请求
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startScan()
        }
    }

    // 开启蓝牙
    val enableBluetoothLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            startScan()
        }
    }





    fun stopScan() {
        isScanning.value = false
        bluetoothAdapter?.bluetoothLeScanner?.stopScan(scanCallback)
    }


    // 连接设备
    fun connectToDevice(device: BluetoothDevice) {
        val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        val socket = device.createRfcommSocketToServiceRecord(uuid)
        try {
            socket.connect()
            connectedDevice.value = device
            isConnected.value = true
        } catch (e: IOException) {
            Log.e("Bluetooth", "连接失败: ${e.message}")
        }
    }

    // UI
    Column(modifier = modifier) {
        Button(onClick = {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (bluetoothAdapter?.isEnabled == true) {
                    startScan()
                } else {
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    enableBluetoothLauncher.launch(enableBtIntent)
                }
            } else {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }) {
            Text(if (isScanning.value) "停止扫描" else "开始扫描")
        }

        Text("扫描到的内容")
        DeviceList(devices) { device ->
            connectToDevice(device)
        }

        if (isConnected.value) {
            Text("已连接到: ${connectedDevice.value?.name}")
        }
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
    val navController = rememberNavController()
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
                            Log.d("MainActivity","Scanned QR Code: $qrCode\"")
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(qrCode))
                            context.startActivity(intent)

//                            onScanResult(qrCode) // 将扫描结果传递给回调
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

//蓝牙功能

@Composable
fun DeviceList(
    devices: List<BluetoothDevice>,
    onDeviceClick: (BluetoothDevice) -> Unit,
) {
    val context = LocalContext.current

    LazyColumn {
        items(devices) { device ->
            Button(
                onClick = { onDeviceClick(device) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return@Button
                }
                Text(
                    text = device.name ?: "Unknown Device",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

//@Composable

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun DefaultPreview() {
//    CameraPermission()
}

//蓝牙连接功能
@Composable
fun BluetoothConnectionButton() {

}