package com.brainasaservice.service

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuCompat
import androidx.core.view.MenuItemCompat
import com.brainasaservice.core.feature.capability.ButtonCapability
import com.brainasaservice.core.feature.capability.MenuCapability
import kotlinx.android.synthetic.main.activity_main.contentLayout
import kotlinx.android.synthetic.main.activity_main.view.contentLayout
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val featureRegistry = FeatureRegistry.getInstance()

        Log.i("Mainn", "We found ${featureRegistry.getList().size} features.")

        featureRegistry.getList().forEach { feature ->
            if (feature.capabilities.any { it is ButtonCapability }) {
                val buttonCapability = feature.capabilities.first { it is ButtonCapability } as ButtonCapability
                val button = Button(this).apply {
                    text = buttonCapability.getText()
                    setOnClickListener { buttonCapability.onClick(this@MainActivity) }
                }
                contentLayout.addView(button)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        FeatureRegistry.getInstance().getList().forEach { feature ->
            if(feature.capabilities.any { it is MenuCapability}) {
                val capability = feature.capabilities.first { it is MenuCapability } as MenuCapability
                menu?.add(capability.getTitle())?.let { capability.config(this@MainActivity, it) }
            }
        }

        return true
    }
}
