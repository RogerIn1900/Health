package com.example.health.feature.records

import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HealthViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var repo: HealthRepository
    private lateinit var vm: HealthViewModel
    @Before fun setUp() {
        Dispatchers.setMain(dispatcher)
        repo = mockk(relaxed = true)
        vm = HealthViewModel(repo, 20_000L)
    }
    @After fun tearDown() = Dispatchers.resetMain()
    @Test fun `invalid input reports error without writing`() = runTest(dispatcher) {
        vm.save("0", "-1", "20"); runCurrent()
        assertTrue(vm.message.value!!.contains("负数"))
        coVerify(exactly = 0) { repo.save(any()) }
        assertFalse(vm.busy.value)
    }
    @Test fun `save captures selected date before asynchronous execution`() = runTest(dispatcher) {
        vm.save("100", "200", "30"); vm.selectDay(20_001L); runCurrent()
        coVerify { repo.save(HealthRecord(LOCAL_PROFILE_ID, 20_000L, 100, 200, 30)) }
    }
    @Test fun `duplicate save does not issue concurrent writes`() = runTest(dispatcher) {
        val pending = CompletableDeferred<Unit>()
        coEvery { repo.save(any()) } coAnswers { pending.await() }
        vm.save("1", "2", "3"); runCurrent(); vm.save("1", "2", "3"); runCurrent()
        coVerify(exactly = 1) { repo.save(any()) }
        pending.complete(Unit); runCurrent(); assertFalse(vm.busy.value)
    }
    @Test fun `database failure is visible and retry is enabled`() = runTest(dispatcher) {
        coEvery { repo.save(any()) } throws IllegalStateException("write failed")
        vm.save("1", "2", "3"); runCurrent()
        assertEquals("write failed", vm.message.value)
        assertFalse(vm.busy.value)
    }
}
