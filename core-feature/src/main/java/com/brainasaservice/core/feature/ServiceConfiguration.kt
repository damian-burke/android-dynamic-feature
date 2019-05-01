package com.brainasaservice.core.feature

import com.brainasaservice.core.feature.capability.ServiceCapability

interface ServiceConfiguration {
    val name: String

    val capabilities: List<ServiceCapability>
}

inline fun <reified T : ServiceCapability> ServiceConfiguration.getCapabilities(): List<T> {
    return capabilities.filter { it is T }.map { it as T }
}
