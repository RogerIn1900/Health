package com.example.health.TopDesign.QRCodeAnalyzer

import android.graphics.ImageFormat
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.health.TopDesign.QRType.QRType
import com.google.zxing.BinaryBitmap
import com.google.zxing.common.HybridBinarizer

class QRCodeAnalyzer(private val onQrCodeScanned: (String, QRType) -> Unit) : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(image: ImageProxy) {
        val mediaImage = image.image
        if (mediaImage != null && image.format == ImageFormat.YUV_420_888) {
            val rotation = image.imageInfo.rotationDegrees
            val source = getLuminanceSource(mediaImage, rotation)
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
            try {
                val result = reader.decode(binaryBitmap)
                val qrType = when {
                    result.text.startsWith("http") -> QRType.URL
                    result.text.startsWith("tel:") -> QRType.TEL
                    result.text.matches(Regex("^geo:[0-9.]+,[0-9.]+$")) -> QRType.GEO
                    else -> QRType.TEXT
                }
                onQrCodeScanned(result.text, qrType)
            } finally {
                image.close()
            }
        }
    }
}