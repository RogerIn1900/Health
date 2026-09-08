package com.example.health.feature

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.health.feature.records.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HealthScreenTest {
    @get:Rule val compose = createComposeRule()
    private lateinit var db: HealthDatabase
    private lateinit var repo: HealthRepository
    @Before fun setUp() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), HealthDatabase::class.java).build()
        repo = HealthRepository(db.records())
        compose.setContent { MaterialTheme { HealthScreen(androidx.lifecycle.viewmodel.compose.viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = HealthViewModel(repo, 20_000L) as T
        })) } }
    }
    @After fun tearDown() = db.close()
    @Test fun editSaveAndDeleteUpdateDatabaseAndChart() {
        compose.onNodeWithTag("steps").performScrollTo().performTextReplacement("1234")
        compose.onNodeWithTag("save").performScrollTo().performClick()
        compose.waitUntil(5_000) { runBlocking { repo.observeRange(LOCAL_PROFILE_ID, 20_000, 20_000).first().singleOrNull()?.steps == 1234 } }
        compose.onNodeWithText("删除当天").performScrollTo().performClick()
        compose.waitUntil(5_000) { runBlocking { repo.observeRange(LOCAL_PROFILE_ID, 20_000, 20_000).first().isEmpty() } }
    }
    @Test fun invalidInputDoesNotCreateARecord() {
        compose.onNodeWithTag("steps").performScrollTo().performTextReplacement("-1")
        compose.onNodeWithTag("save").performScrollTo().performClick()
        compose.onNodeWithTag("message").performScrollTo().assertTextContains("负数", substring = true)
        assertTrue(runBlocking { repo.observeRange(LOCAL_PROFILE_ID, 20_000, 20_000).first().isEmpty() })
    }
    @Test fun scannerWithoutPermissionCanBeClosedAndRecordsRemainEditable() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        org.junit.Assume.assumeTrue(context.checkSelfPermission(android.Manifest.permission.CAMERA) != android.content.pm.PackageManager.PERMISSION_GRANTED)
        compose.onNodeWithText("扫描二维码").performScrollTo().performClick()
        compose.onNodeWithText("扫描二维码需要相机权限；拒绝后仍可使用健康记录。").performScrollTo().assertIsDisplayed()
        compose.onNodeWithText("关闭扫描").performScrollTo().performClick()
        compose.onNodeWithText("允许相机").assertDoesNotExist()
        compose.onNodeWithTag("steps").performScrollTo().performTextReplacement("42")
        compose.onNodeWithTag("save").performScrollTo().performClick()
        compose.waitUntil(5_000) { runBlocking { repo.observeRange(LOCAL_PROFILE_ID, 20_000, 20_000).first().singleOrNull()?.steps == 42 } }
    }

}
