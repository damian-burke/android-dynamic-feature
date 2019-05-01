package com.brainasaservice.service

import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.brainasaservice.core.feature.capability.ImageProcessorCapability
import com.brainasaservice.core.feature.capability.ViewCapability
import com.brainasaservice.core.feature.getCapabilities
import kotlinx.android.synthetic.main.activity_main.contentLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceRegistry = ServiceRegistry.getInstance()

        val drawable = ContextCompat.getDrawable(this, R.drawable.bench)
        var bitmap = drawable?.toBitmap()

        val imageView = ImageView(this).apply {
            setImageBitmap(bitmap)
        }

        var index = 1

        serviceRegistry.getList().forEach { feature ->
            feature.getCapabilities<ViewCapability>().forEach { capability ->
                contentLayout.addView(capability.inflate(this, contentLayout), index++)
            }
        }

        contentLayout.addView(imageView, index)

        serviceRegistry.getList().filter { conf -> conf.capabilities.any { it is ImageProcessorCapability } }
                .forEach {
                    it.getCapabilities<ImageProcessorCapability>().forEach { imageProcessorCapability ->
                        bitmap?.let { bmp ->
                            bitmap = imageProcessorCapability.process(bmp)
                        }
                    }
                }

        Handler().postDelayed({
            imageView.setImageBitmap(bitmap)
        }, 3000)
    }
}
