package io.github.sangcomz.commercenavigation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationEntryPointRegistry @Inject constructor(
    private val factories: Set<@JvmSuppressWildcards FeatureEntryPointFactory>
) {
    fun register(builder: EntryProviderScope<NavKey>, navigator: Navigator) {
        factories.forEach { it.register(builder, navigator) }
    }
}
