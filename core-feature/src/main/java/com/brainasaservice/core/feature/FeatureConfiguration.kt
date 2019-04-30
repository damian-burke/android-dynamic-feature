package com.brainasaservice.core.feature

import com.brainasaservice.core.feature.capability.FeatureCapability

interface FeatureConfiguration {
    val name: String

    val capabilities: List<FeatureCapability>
}

/**
 * Requirements:
 * - Know about feature-scope before it's been installed, to offer installation.
 * - Feature injects capabilities into App.
 *
 */
