package com.brainasaservice.service

import android.util.Log
import com.brainasaservice.core.feature.FeatureConfiguration

interface FeatureRegistry {
    fun register(feature: FeatureConfiguration)

    fun getList(): List<FeatureConfiguration>

    companion object {
        private val _instance by lazy { FeatureRegistryImpl() }

        fun getInstance(): FeatureRegistry = _instance
    }
}

class FeatureRegistryImpl internal constructor() : FeatureRegistry {
    private val list: MutableList<FeatureConfiguration> = mutableListOf()

    override fun getList(): List<FeatureConfiguration> = list

    override fun register(feature: FeatureConfiguration) {
        Log.i("FeatureRegistry", "register(feature=$feature)")
        list.add(feature)
    }
}
