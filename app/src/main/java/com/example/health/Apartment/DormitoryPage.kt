package com.example.health.Apartment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun DormitoryList() {
    // 生成5层数据，每层20个元素（101-120, 201-220...501-520）
    val matrixData: List<List<String>> = (1..5).map { floor ->
        (1..20).map { index ->
            // 格式：楼层号（1位） + 两位元素编号（补零）
            "${floor}${String.format("%02d", index)}"
        }
    }.toMutableList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // 使用 itemsIndexed 获取行索引和数据
        itemsIndexed(matrixData.reversed()) { rowIndex, rowData ->
            // 单独提取行数据（此处可添加预处理逻辑）
            val processedRow = remember(rowData) {
                // 示例处理：为每个元素添加行号前缀
                rowData.mapIndexed { colIndex, item ->
                    "宿舍号：$item"
//                    "R${rowIndex + 1}-C${colIndex + 1}: $item"
                }
            }

            // 行标题
            val floor = matrixData.size - rowIndex
            Text(
                text = "第 ${floor} 层",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            // 水平滚动行内容
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(processedRow) { item ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Text(
                            text = item,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // 行分隔符（排除最后一行）
            if (rowIndex < matrixData.size - 1) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    thickness = 1.dp
                )
            }
        }
    }
}