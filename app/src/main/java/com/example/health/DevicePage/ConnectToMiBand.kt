package com.example.health.DevicePage
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import android.bluetooth.BluetoothSocket
import java.io.IOException
import java.util.UUID

@Composable
fun RequestBluetoothPermissions(onPermissionsGranted: () -> Unit) {
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        if (permissions.values.all { it }) {
            onPermissionsGranted()
        }
    }

    // 请求权限
    permissionLauncher.launch(arrayOf(
        android.Manifest.permission.BLUETOOTH,
        android.Manifest.permission.BLUETOOTH_ADMIN,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.BLUETOOTH_SCAN,
        android.Manifest.permission.BLUETOOTH_CONNECT
    ))
}

@Composable
fun ConnectToMiBand() {
    RequestBluetoothPermissions {
        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // 设备不支持蓝牙
            Log.e("Bluetooth", "Device does not support Bluetooth.")
        } else {
            if (!bluetoothAdapter.isEnabled) {
                // 提示用户启用蓝牙
                Log.e("Bluetooth", "Please enable Bluetooth.")
            } else {
                // 开始搜索 Mi Band
                val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
                for (device in pairedDevices) {
                    if (device.name.contains("Mi Band 9", ignoreCase = true)) {
                        // 找到 Mi Band 9，进行连接
                        connectToDevice(device)
                    }
                }
            }
        }
    }
}



fun connectToDevice(device: BluetoothDevice) {
    // UUID 是小米手环的特定 UUID
    val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // 示例 UUID

    try {
        val socket: BluetoothSocket = device.createRfcommSocketToServiceRecord(uuid)
        socket.connect() // 连接到设备

        // 连接成功后，可以在这里进行数据交换
    } catch (e: IOException) {
        Log.e("Bluetooth", "Connection failed: ${e.message}")
    }
}

