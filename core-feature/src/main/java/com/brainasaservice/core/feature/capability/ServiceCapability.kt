package com.brainasaservice.core.feature.capability

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup

interface ServiceCapability

/**
 * Simple capability that allows the service to inflate and return a View, ready to be used
 * within the host.
 */
interface ViewCapability : ServiceCapability {
    fun inflate(context: Context, parent: ViewGroup?): View
}

/**
 * Capability that enables the service to perform certain actions on Bitmaps.
 */
interface ImageProcessorCapability : ServiceCapability {
    fun process(bitmap: Bitmap): Bitmap
}
