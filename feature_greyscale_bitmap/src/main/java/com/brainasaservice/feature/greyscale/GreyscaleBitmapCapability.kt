package com.brainasaservice.feature.greyscale

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.brainasaservice.core.feature.capability.ImageProcessorCapability

class GreyscaleBitmapCapability : ImageProcessorCapability {
    override fun process(bitmap: Bitmap): Bitmap {
        val height = bitmap.height
        val width = bitmap.width

        val copy = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(copy)
        val paint = Paint()
        val colorMatrix = ColorMatrix().apply {
            setSaturation(0f)
        }
        val colorMatrixFilter = ColorMatrixColorFilter(colorMatrix)
        paint.setColorFilter(colorMatrixFilter)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return copy
    }
}
