package com.brainasaservice.feature.profile

import com.brainasaservice.core.feature.ServiceConfiguration
import com.brainasaservice.core.feature.capability.ServiceCapability

class ProfileServiceConfiguration : ServiceConfiguration {
    override val name: String = "Profile"

    override val capabilities: List<ServiceCapability> = listOf(
            ProfileViewCapability()
    )
}
