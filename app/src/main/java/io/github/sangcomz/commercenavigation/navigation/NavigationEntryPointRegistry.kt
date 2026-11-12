package io.github.sangcomz.commercenavigation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPoint
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationEntryPointRegistry @Inject constructor(
    private val factories: Set<@JvmSuppressWildcards FeatureEntryPointFactory>
) {
    fun entries(): List<FeatureEntryPoint> = factories.flatMap { it.create() }
}
