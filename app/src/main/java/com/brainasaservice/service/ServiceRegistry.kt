package com.brainasaservice.service

import android.util.Log
import com.brainasaservice.core.feature.ServiceConfiguration

interface ServiceRegistry {
    fun register(service: ServiceConfiguration)

    fun getList(): List<ServiceConfiguration>

    companion object {
        private val _instance by lazy { ServiceRegistryImpl() }

        fun getInstance(): ServiceRegistry = _instance
    }
}

class ServiceRegistryImpl internal constructor() : ServiceRegistry {
    private val list: MutableList<ServiceConfiguration> = mutableListOf()

    override fun getList(): List<ServiceConfiguration> = list

    override fun register(service: ServiceConfiguration) {
        Log.i("ServiceRegistry", "register(service=$service)")
        list.add(service)
    }
}
