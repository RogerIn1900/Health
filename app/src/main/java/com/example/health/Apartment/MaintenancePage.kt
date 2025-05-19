package com.example.health.Apartment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


// Data models based on the OpenAPI specs
data class MaintenanceRequest(
    val id: Int,
    val dormitoryId: Int,
    val description: String,
    val urgency: Int,  // 1-low, 2-medium, 3-high
    val status: Int,   // 0-pending, 1-in progress, 2-completed
    val feedback: String? = null,
    val createdAt: String,
    val dormitoryInfo: String  // e.g. "Building A, Room 101"
)

class MaintenanceViewModel : ViewModel() {

    private val _maintenanceRequests = mutableStateOf<List<MaintenanceRequest>>(emptyList())
    val maintenanceRequests: State<List<MaintenanceRequest>> = _maintenanceRequests

    private val _selectedStatus = mutableStateOf<Int?>(null)
    val selectedStatus: State<Int?> = _selectedStatus

    init {
        fetchMaintenanceRequests()
    }

    fun setStatusFilter(status: Int?) {
        _selectedStatus.value = status
        fetchMaintenanceRequests()
    }

    fun submitMaintenanceRequest(description: String, urgency: Int, dormitoryId: Int) {
        viewModelScope.launch {
            // API call would go here
            val newRequest = MaintenanceRequest(
                id = (100..999).random(),
                dormitoryId = dormitoryId,
                description = description,
                urgency = urgency,
                status = 0,
                createdAt = "2025-05-17",
                dormitoryInfo = "Building A, Room 101"
            )

            _maintenanceRequests.value = listOf(newRequest) + _maintenanceRequests.value
        }
    }

    private fun fetchMaintenanceRequests() {
        // This would be an API call in a real app
        // For preview, we'll use sample data
        _maintenanceRequests.value = listOf(
            MaintenanceRequest(
                id = 1001,
                dormitoryId = 42,
                description = "Broken window in common area",
                urgency = 2,
                status = 0,
                createdAt = "2025-05-15",
                dormitoryInfo = "Building A, Room 101"
            ),
            MaintenanceRequest(
                id = 1002,
                dormitoryId = 42,
                description = "Water leaking from ceiling",
                urgency = 3,
                status = 1,
                createdAt = "2025-05-14",
                dormitoryInfo = "Building A, Room 101"
            ),
            MaintenanceRequest(
                id = 1003,
                dormitoryId = 42,
                description = "Light bulb replacement needed",
                urgency = 1,
                status = 2,
                feedback = "Thank you for the prompt service!",
                createdAt = "2025-05-10",
                dormitoryInfo = "Building A, Room 101"
            ),
            MaintenanceRequest(
                id = 1004,
                dormitoryId = 42,
                description = "Bathroom sink is clogged",
                urgency = 2,
                status = 2,
                feedback = "Fixed properly, works great now",
                createdAt = "2025-05-08",
                dormitoryInfo = "Building A, Room 101"
            )
        ).filter { request ->
            selectedStatus.value?.let { status -> request.status == status } ?: true
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DormitoryMaintenanceScreen(
    viewModel: MaintenanceViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val maintenanceRequests = viewModel.maintenanceRequests.value
    val selectedStatus = viewModel.selectedStatus.value

    var showNewRequestDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showNewRequestDialog = true },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add maintenance request"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Status filter tabs
            StatusFilterTabs(
                selectedStatus = selectedStatus,
                onStatusSelected = { viewModel.setStatusFilter(it) }
            )

            // Maintenance requests list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(maintenanceRequests) { request ->
                    MaintenanceRequestCard(request = request)
                }

                // Show empty state if no requests
                if (maintenanceRequests.isEmpty()) {
                    item {
                        EmptyStateMessage(selectedStatus)
                    }
                }
            }
        }
    }

    // New maintenance request dialog
    if (showNewRequestDialog) {
        NewMaintenanceRequestDialog(
            onDismiss = { showNewRequestDialog = false },
            onSubmit = { description, urgency ->
                viewModel.submitMaintenanceRequest(description, urgency, 42)
                showNewRequestDialog = false
            }
        )
    }
}

@Composable
fun StatusFilterTabs(
    selectedStatus: Int?,
    onStatusSelected: (Int?) -> Unit
) {
    val tabs = listOf(
        Triple(null, "全部", null),
        Triple(0, "待处理", Icons.Outlined.Warning),
        Triple(1, "处理中", Icons.Outlined.Build),
        Triple(2, "已完成", Icons.Outlined.Check)
    )

    ScrollableTabRow(
        selectedTabIndex = tabs.indexOfFirst { it.first == selectedStatus }.let { if (it == -1) 0 else it },
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        edgePadding = 16.dp
    ) {
        tabs.forEachIndexed { index, (status, label, icon) ->
            Tab(
                selected = status == selectedStatus,
                onClick = { onStatusSelected(status) },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (icon != null) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(label)
                    }
                }
            )
        }
    }
}

@Composable
fun MaintenanceRequestCard(request: MaintenanceRequest) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Header with status indicator and urgency
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Status indicator
                val (statusColor, statusText) = when (request.status) {
                    0 -> Pair(Color(0xFFFFA000), "待处理")
                    1 -> Pair(Color(0xFF2196F3), "处理中")
                    2 -> Pair(Color(0xFF4CAF50), "已完成")
                    else -> Pair(Color.Gray, "未知")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(color = statusColor, shape = RoundedCornerShape(5.dp))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = statusText,
                        fontSize = 14.sp,
                        color = statusColor
                    )
                }

                // Urgency indicator
                val urgencyText = when (request.urgency) {
                    1 -> "低优先级"
                    2 -> "中优先级"
                    3 -> "高优先级"
                    else -> "未指定"
                }

                val urgencyColor = when (request.urgency) {
                    1 -> Color(0xFF4CAF50)
                    2 -> Color(0xFFFFA000)
                    3 -> Color(0xFFF44336)
                    else -> Color.Gray
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (request.urgency == 3) {
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = urgencyColor
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                    }
                    Text(
                        text = urgencyText,
                        fontSize = 12.sp,
                        color = urgencyColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Location info
            Text(
                text = request.dormitoryInfo,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = request.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Date
            Text(
                text = "提交时间: ${request.createdAt}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Feedback section (only for completed requests)
            if (request.status == 2 && !request.feedback.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(12.dp))
                Divider()
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "用户反馈",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = request.feedback,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Action buttons for different statuses
            when (request.status) {
                0 -> {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            onClick = { /* Cancel request */ },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("取消申请")
                        }
                    }
                }
                1 -> {
                    // No special actions for in-progress
                }
                2 -> {
                    // If no feedback yet, show feedback button
                    if (request.feedback.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = { /* Submit feedback */ }
                            ) {
                                Text("提交反馈")
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMaintenanceRequestDialog(
    onDismiss: () -> Unit,
    onSubmit: (description: String, urgency: Int) -> Unit
) {
    var description by remember { mutableStateOf("") }
    var urgency by remember { mutableStateOf(2) } // Default to medium

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("新建维修申请")
        },
        text = {
            Column {
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("问题描述") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = { Text("请详细描述维修问题...") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("紧急程度", fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UrgencyOption(
                        text = "低",
                        color = Color(0xFF4CAF50),
                        selected = urgency == 1,
                        onClick = { urgency = 1 }
                    )

                    UrgencyOption(
                        text = "中",
                        color = Color(0xFFFFA000),
                        selected = urgency == 2,
                        onClick = { urgency = 2 }
                    )

                    UrgencyOption(
                        text = "高",
                        color = Color(0xFFF44336),
                        selected = urgency == 3,
                        onClick = { urgency = 3 }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onSubmit(description, urgency) },
                enabled = description.isNotBlank()
            ) {
                Text("提交")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}

@Composable
fun UrgencyOption(
    text: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) color.copy(alpha = 0.2f) else Color.Transparent
    val borderColor = if (selected) color else Color.Gray.copy(alpha = 0.5f)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (selected) color else Color.Gray,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun EmptyStateMessage(selectedStatus: Int?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.Build,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val message = when (selectedStatus) {
            0 -> "当前没有待处理的维修申请"
            1 -> "当前没有正在处理中的维修申请"
            2 -> "当前没有已完成的维修申请"
            else -> "当前没有维修申请记录"
        }

        Text(
            text = message,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "点击右下角加号创建新的维修申请",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
            textAlign = TextAlign.Center
        )
    }
}

// Preview function
@Preview(showBackground = true)
@Composable
fun PreviewDormitoryMaintenanceScreen() {
    MaterialTheme {
        DormitoryMaintenanceScreen()
    }
}