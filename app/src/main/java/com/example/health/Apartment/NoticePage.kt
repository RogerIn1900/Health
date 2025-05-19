package com.example.health.Apartment

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun NoticeBoardScreen() {
//    val notices by viewModel.notices.collectAsState()


    // 示例数据列表（可直接用于 ViewModel 或 Compose 的初始状态）
    val sampleNotices = listOf(
        Notice(
            id = 1,
            title = "宿舍安全通知",
            content = "本周五晚进行消防演练，请保持走廊畅通，禁止堆放杂物。",
            createTime = "2024-03-20 10:00:00"
        ),
        Notice(
            id = 2,
            title = "热水系统维护",
            content = "4月1日凌晨2:00-4:00暂停热水供应，请提前蓄水。",
            createTime = "2024-03-25 15:30:00"
        ),
        Notice(
            id = 3,
            title = "校园网缴费提醒",
            content = "学生宿舍校园网费用将于3月31日截止，请及时充值。",
            createTime = "2024-03-28 09:45:00"
        ),
        Notice(
            id = 4,
            title = "自习室开放通知",
            content = "新学期图书馆自习室开放时间为6:00-23:00，欢迎使用。",
            createTime = "2024-04-01 12:00:00"
        ),
        Notice(
            id = 5,
            title = "节假日调休安排",
            content = "五一劳动节放假调休：4月28日（周日）、5月5日（周日）正常上课。",
            createTime = "2024-04-10 16:20:00"
        )
    )
    LazyColumn() {
        items(sampleNotices) { notice ->
            NoticeItem(notice)
        }
    }



}

@Composable
private fun NoticeItem(notice: Notice) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = notice.title, style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = notice.content, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "发布时间: ${notice.createTime}",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface
            )
        }
    }
}

// ViewModel中调用接口
class NoticeViewModel : ViewModel() {
    private val _notices = MutableStateFlow<List<Notice>>(emptyList())
    val notices: StateFlow<List<Notice>> = _notices

    init {
        fetchNotices()
    }

    private fun fetchNotices() {
        viewModelScope.launch {
            try {
//                val response = RetrofitClient.api.getNotices()
//                if (response.isSuccessful) {
//                    _notices.value = response.body() ?: emptyList()
//                } else {
//                    Log.e("NoticeViewModel", "Failed to fetch notices: ${response.code()}")
//                }
            } catch (e: Exception) {
                Log.e("NoticeViewModel", "Network error: ${e.message}")
            }
        }
    }
}



// 数据模型
data class Notice(
    val id: Int,
    val title: String,
    val content: String,
    val createTime: String
)

@Preview
@Composable
fun NoticeBoardScreenPreview(){
    NoticeBoardScreen()
}