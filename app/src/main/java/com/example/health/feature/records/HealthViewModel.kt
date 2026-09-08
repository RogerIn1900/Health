package com.example.health.feature.records

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// The current app stores one local profile; no remote login or health sensor collection is implied.
const val LOCAL_PROFILE_ID = "local-profile"
const val HISTORY_DAYS = 7L
// Keep the Room observer through short UI rebindings (milliseconds).
private const val UI_SUBSCRIPTION_TIMEOUT_MS = 5_000L

@OptIn(ExperimentalCoroutinesApi::class)
class HealthViewModel(private val repository: HealthRepository, initialDay: Long) : ViewModel() {
    private val selectedDay = MutableStateFlow(initialDay)
    val day = selectedDay.asStateFlow()
    val records = day.flatMapLatest { selected ->
        repository.observeRange(LOCAL_PROFILE_ID, selected - HISTORY_DAYS + 1, selected)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(UI_SUBSCRIPTION_TIMEOUT_MS), emptyList())
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()
    private val _busy = MutableStateFlow(false)
    val busy = _busy.asStateFlow()

    fun selectDay(epochDay: Long) { selectedDay.value = epochDay; _message.value = null }
    fun save(calories: String, steps: String, minutes: String) {
        val targetDay = day.value
        update {
            val record = HealthRecord(LOCAL_PROFILE_ID, targetDay,
                calories.toIntOrNull() ?: error("请输入整数热量"),
                steps.toIntOrNull() ?: error("请输入整数步数"),
                minutes.toIntOrNull() ?: error("请输入整数活动分钟"))
            repository.save(record)
        }
    }
    fun delete() {
        val targetDay = day.value
        update { repository.delete(LOCAL_PROFILE_ID, targetDay) }
    }
    private fun update(action: suspend () -> Unit) {
        if (_busy.value) return
        _busy.value = true
        viewModelScope.launch {
            try {
                action()
                _message.value = "记录已更新"
            } catch (cancelled: CancellationException) {
                throw cancelled
            } catch (failure: Exception) {
                _message.value = failure.message ?: "保存失败，请重试"
            } finally { _busy.value = false }
        }
    }
}
