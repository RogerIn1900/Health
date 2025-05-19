//package com.example.health.Apartment
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material3.Card
//import androidx.compose.material3.Divider
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.SegmentedButtonDefaults.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//class HygienePage {
//}
//
//// 1. 数据类定义
//data class InspectionItem(
//    val dorId: Int,
//    val buildingNo: Int,
//    val roomNo: Int,
//    val floorNo: Int,
//    val score: Int,
//    val comment: String,
//    val checkTime: String
//)
//
//// 2. ViewModel 实现
////@HiltViewModel
//class InspectionViewModel @Inject constructor() : ViewModel() {
//    private val _items = mutableStateOf<List<InspectionItem>>(emptyList())
//    val items: State<List<InspectionItem>> = _items
//
//    init {
//        viewModelScope.launch {
//            // 模拟数据加载
//            val mockData = listOf(
//                InspectionItem(
//                    dorId = 1,
//                    buildingNo = 25,
//                    roomNo = 101,
//                    floorNo = 1,
//                    score = 72,
//                    comment = "桌子不干净",
//                    checkTime = "2025-05-16"
//                ),
//                InspectionItem(
//                    dorId = 1,
//                    buildingNo = 25,
//                    roomNo = 101,
//                    floorNo = 1,
//                    score = 72,
//                    comment = "厕所不干净",
//                    checkTime = "2025-04-12"
//                )
//            )
//            _items.value = mockData
//        }
//    }
//}
//
//// 3. 主界面实现
//@Composable
//fun InspectionReportScreen(viewModel: InspectionViewModel = hiltViewModel()) {
//    val items by viewModel.items.collectAsState()
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("宿舍检查报告") },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
//            )
//        }
//    ) { padding ->
//        if (items.isEmpty()) {
//            Center(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding),
//                content = {
//                    Text("暂无检查记录", style = MaterialTheme.typography.headlineMedium)
//                }
//            )
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding),
//                contentPadding = PaddingValues(bottom = 16.dp)
//            ) {
//                items(items) { item ->
//                    InspectionItemCard(item = item)
//                }
//            }
//        }
//    }
//}
//
//// 4. 列表项组件
//@Composable
//fun InspectionItemCard(item: InspectionItem) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 8.dp),
//        elevation = 4.dp
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            // 基本信息
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = "栋号：${item.buildingNo} 栋 ${item.roomNo} 室",
//                    style = MaterialTheme.typography.titleMedium,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = "评分：${item.score}/100",
//                    style = MaterialTheme.typography.bodyLarge.copy(
//                        color = when {
//                            item.score >= 90 -> MaterialTheme.colorScheme.outline
//                            item.score >= 75 -> MaterialTheme.colorScheme.primary
//                            else -> MaterialTheme.colorScheme.error
//                        }
//                    )
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // 检查内容
//            Text(
//                text = "检查内容：",
//                style = MaterialTheme.typography.bodyMedium,
//                fontWeight = FontWeight.Bold
//            )
//            Row(
//                modifier = Modifier.padding(start = 16.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
////                Icon(Icons.Default., contentDescription = "评论", tint = MaterialTheme.colorScheme.onSurfaceVariant)
//                Spacer(modifier = Modifier.width(8.dp))
//                Text(
//                    text = item.comment,
//                    style = MaterialTheme.typography.bodyMedium,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // 时间信息
//            Text(
//                text = "检查时间：${item.checkTime}",
//                style = MaterialTheme.typography.bodySmall.copy(
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
//                )
//            )
//        }
//    }
//}