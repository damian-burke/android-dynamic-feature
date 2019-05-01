package com.brainasaservice.feature.greyscale

import com.brainasaservice.core.feature.ServiceConfiguration
import com.brainasaservice.core.feature.capability.ServiceCapability

class GreyscaleServiceConfiguration : ServiceConfiguration {
    override val name: String = "Greyscale"

    override val capabilities: List<ServiceCapability> = listOf(
            GreyscaleBitmapCapability()
    )
}
