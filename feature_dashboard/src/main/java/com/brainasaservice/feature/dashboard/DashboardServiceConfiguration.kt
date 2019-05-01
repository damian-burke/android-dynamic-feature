package com.brainasaservice.feature.dashboard

import android.util.Log
import com.brainasaservice.core.feature.ServiceConfiguration
import com.brainasaservice.core.feature.capability.ServiceCapability

class DashboardServiceConfiguration : ServiceConfiguration {
    override val name: String = "Dashboard"

    override val capabilities: List<ServiceCapability> = listOf(
            DashboardViewCapability().also { Log.i("Dashboard", "Adding DashboardViewCapability") }
    )
}
