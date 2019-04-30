package com.brainasaservice.feature.profile

import android.view.MenuItem
import androidx.core.view.MenuItemCompat
import com.brainasaservice.core.feature.capability.MenuCapability

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
            override val name: String = "Profile"

            override val capabilities: List<FeatureCapability> = listOf(
                    object : ButtonCapability {
                        override fun getText(): String = "Profile"

                        override fun onClick(context: Context) {
                            context.startActivity(Intent(context, ProfileActivity::class.java))
                        }
                    },
                    object : MenuCapability {
                        override fun getTitle(): String = "Profile"

                        override fun config(context: Context, item: MenuItem) {
                            item.setIcon(android.R.drawable.ic_menu_info_details)
                            item.setOnMenuItemClickListener {
                                Log.i("Service", "onClickMenuItem()")
                                context.startActivity(Intent(context, ProfileActivity::class.java))
                                true
                            }
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
