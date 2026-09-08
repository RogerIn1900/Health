package com.example.health.feature.scan

/** Extract only Y; row padding and interleaved pixels must not reach the decoder. */
fun copyLuminance(bytes: ByteArray, width: Int, height: Int, rowStride: Int, pixelStride: Int): ByteArray {
    require(width > 0 && height > 0 && pixelStride > 0 && rowStride > 0)
    require(width.toLong() * height <= Int.MAX_VALUE)
    require((width - 1L) * pixelStride < rowStride)
    val last = (height - 1L) * rowStride + (width - 1L) * pixelStride
    require(last < bytes.size) { "Incomplete luminance plane" }
    return ByteArray(width * height) { index -> bytes[(index / width) * rowStride + (index % width) * pixelStride] }
}

data class LuminanceImage(val bytes: ByteArray, val width: Int, val height: Int)

fun rotateLuminance(bytes: ByteArray, width: Int, height: Int, degrees: Int): LuminanceImage {
    require(width > 0 && height > 0 && width.toLong() * height == bytes.size.toLong())
    require(degrees in listOf(0, 90, 180, 270)) { "Rotation must be clockwise degrees" }
    val swap = degrees == 90 || degrees == 270
    val outputWidth = if (swap) height else width
    val output = ByteArray(bytes.size)
    for (y in 0 until height) for (x in 0 until width) {
        val (ox, oy) = when (degrees) {
            90 -> height - 1 - y to x
            180 -> width - 1 - x to height - 1 - y
            270 -> y to width - 1 - x
            else -> x to y
        }
        output[oy * outputWidth + ox] = bytes[y * width + x]
    }
    return LuminanceImage(output, outputWidth, if (swap) width else height)
}
