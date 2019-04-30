package com.brainasaservice.feature.dashboard

import android.content.BroadcastReceiver
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.brainasaservice.core.feature.FeatureConfiguration
import com.brainasaservice.core.feature.capability.ButtonCapability
import com.brainasaservice.core.feature.capability.FeatureCapability
import com.brainasaservice.service.FeatureRegistry

class ServiceProvider : ContentProvider() {
    override fun insert(p0: Uri, p1: ContentValues?): Uri? = null

    override fun query(p0: Uri, p1: Array<String>?, p2: String?, p3: Array<String>?, p4: String?): Cursor? = null

    override fun onCreate(): Boolean {
        Log.i("Service", "Initializing...")
        val x = object : FeatureConfiguration {
            override val name: String = "Dashboard"

            override val capabilities: List<FeatureCapability> = listOf(
                    object : ButtonCapability {
                        override fun getText(): String = "Dashboard"

                        override fun onClick(context: Context) {
                            context.startActivity(Intent(context, DashboardActivity::class.java))
                        }
                    }
            )
        }
        FeatureRegistry.getInstance().register(x)
        return true
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<String>?): Int = 0

    override fun delete(p0: Uri, p1: String?, p2: Array<String>?): Int = 0

    override fun getType(p0: Uri): String? = null
}
