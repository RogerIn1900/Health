package com.example.health.DevicePage
import android.Manifest
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
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Alignment
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.lang.reflect.Modifier
import java.util.UUID

@Composable
fun MiBandApp(viewModel: MiBandViewModel) {
    val steps by viewModel.steps.collectAsState()
    val isConnected by viewModel.isConnected.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isConnected) "Connected to Mi Band 9" else "Disconnected",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Steps: $steps",
            style = MaterialTheme.typography.h4
        )
    }
}