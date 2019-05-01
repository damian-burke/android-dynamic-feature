package com.brainasaservice.feature.greyscale

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.brainasaservice.service.ServiceRegistry

class ServiceProvider : ContentProvider() {
    override fun insert(p0: Uri, p1: ContentValues?): Uri? = null

    override fun query(p0: Uri, p1: Array<String>?, p2: String?, p3: Array<String>?, p4: String?): Cursor? = null

    override fun onCreate(): Boolean {
        Log.i("Service", "Initializing Greyscaler...")
        ServiceRegistry.getInstance().register(GreyscaleServiceConfiguration())
        return true
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<String>?): Int = 0

    override fun delete(p0: Uri, p1: String?, p2: Array<String>?): Int = 0

    override fun getType(p0: Uri): String? = null
}
