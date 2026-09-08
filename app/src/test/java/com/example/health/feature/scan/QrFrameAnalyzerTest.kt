package com.example.health.feature.scan

import android.graphics.ImageFormat
import androidx.camera.core.ImageInfo
import androidx.camera.core.ImageProxy
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import io.mockk.*
import java.nio.ByteBuffer
import org.junit.Assert.*
import org.junit.Test

class QrFrameAnalyzerTest {
    private fun image(bytes: ByteArray, width: Int, height: Int): ImageProxy {
        val plane = mockk<ImageProxy.PlaneProxy>()
        every { plane.buffer } returns ByteBuffer.wrap(bytes)
        every { plane.rowStride } returns width
        every { plane.pixelStride } returns 1
        val info = mockk<ImageInfo>()
        every { info.rotationDegrees } returns 0
        return mockk<ImageProxy>(relaxed = true).also {
            every { it.format } returns ImageFormat.YUV_420_888
            every { it.width } returns width
            every { it.height } returns height
            every { it.planes } returns arrayOf(plane)
            every { it.imageInfo } returns info
        }
    }
    @Test fun `unsupported frame is always closed`() {
        val image = mockk<ImageProxy>(relaxed = true)
        every { image.format } returns 0
        QrFrameAnalyzer(ScanGate { 0L }) { fail("Unexpected result") }.analyze(image)
        verify(exactly = 1) { image.close() }
    }
    @Test fun `malformed plane is closed without a result`() {
        val image = image(byteArrayOf(1), 2, 2)
        QrFrameAnalyzer(ScanGate { 0L }) { fail("Unexpected result") }.analyze(image)
        verify(exactly = 1) { image.close() }
    }
    @Test fun `real QR pixels decode and repeated frame is suppressed`() {
        val width = 128
        val matrix = QRCodeWriter().encode("https://example.test", BarcodeFormat.QR_CODE, width, width)
        val bytes = ByteArray(width * width) { if (matrix[it % width, it / width]) 0 else 255.toByte() }
        val results = mutableListOf<ScanResult>()
        val analyzer = QrFrameAnalyzer(ScanGate { 0L }) { results += it }
        val first = image(bytes, width, width); val second = image(bytes, width, width)
        analyzer.analyze(first); analyzer.analyze(second)
        assertEquals(listOf(ScanResult.Link("https://example.test")), results)
        verify(exactly = 1) { first.close() }
        verify(exactly = 1) { second.close() }
    }
}
