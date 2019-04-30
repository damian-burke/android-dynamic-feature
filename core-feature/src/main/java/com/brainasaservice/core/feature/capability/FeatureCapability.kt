package com.brainasaservice.core.feature.capability

import android.content.Context
import android.view.MenuItem

interface FeatureCapability

interface ButtonCapability : FeatureCapability {
    fun getText(): String

    fun onClick(context: Context)
}

interface MenuCapability: FeatureCapability {
    fun config(context: Context, item: MenuItem)

    fun getTitle(): String
}
