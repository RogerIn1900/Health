package com.example.health.TopDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.example.health.R


@Composable
fun HomeTop(title: String){
    TopAppBar(title = { Text(title.toString(),modifier = Modifier.size(36.dp))})
}

@Composable
fun DropdownMenuButton() {
    var showDialog by remember { mutableStateOf(false) }

    IconButton(onClick = { showDialog = true }) {
        Icon(Icons.Default.AddCircle,
            contentDescription = "更多选项",
            modifier = Modifier.size(36.dp)
        )
    }

    // 显示对话框
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(30.dp)
                    )

            ){
                Surface(shape = MaterialTheme.shapes.medium,
                    elevation = 8.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                            .background(Color.White),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = {
                                showDialog = false
                            },
                            modifier = Modifier
                                .width(100.dp)
                        ) {
                            Text("添加设备")
                        }
                        Button(
                            onClick = {
                                showDialog = false
                            },
                            modifier = Modifier
                                .width(100.dp)
                                .background(Color.White)

                        ) {
                            Text("扫一扫")
                        }
                    }
                }
            }
        }
    }
}
