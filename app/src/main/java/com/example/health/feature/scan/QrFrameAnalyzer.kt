package com.example.health.feature.scan

import android.graphics.ImageFormat
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer

class QrFrameAnalyzer(
    private val gate: ScanGate,
    private val onResult: (ScanResult) -> Unit
) : ImageAnalysis.Analyzer {
    private val reader = MultiFormatReader()
    override fun analyze(image: ImageProxy) {
        try {
            if (image.format != ImageFormat.YUV_420_888 || image.planes.isEmpty()) return
            val plane = image.planes[0]
            val buffer = plane.buffer.duplicate()
            val source = ByteArray(buffer.remaining()).also { buffer.get(it) }
            val packed = copyLuminance(source, image.width, image.height, plane.rowStride, plane.pixelStride)
            val rotated = rotateLuminance(packed, image.width, image.height, image.imageInfo.rotationDegrees)
            val luminance = PlanarYUVLuminanceSource(rotated.bytes, rotated.width, rotated.height, 0, 0, rotated.width, rotated.height, false)
            val result = reader.decode(BinaryBitmap(HybridBinarizer(luminance)))
            if (gate.accept(result.text)) onResult(classifyScan(result.text))
        } catch (_: NotFoundException) {
            // Most camera frames do not contain a barcode.
        } catch (_: IllegalArgumentException) {
            // Unsupported/incomplete frame; allow the next frame to be processed.
        } finally {
            reader.reset()
            image.close()
        }
    }
}
