package io.github.sangcomz.commercenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import io.github.sangcomz.commercenavigation.core.navigation.navigation3.Navigation3Navigator
import androidx.navigation3.runtime.NavKey

@Composable
fun AppNavHost(startRoute: NavigationRoute, registry: NavigationEntryPointRegistry) {
    require(startRoute is NavKey)
    val backStack = rememberNavBackStack(startRoute)
    val navigator = remember(backStack) { Navigation3Navigator(backStack) }
    NavDisplay(
        backStack = backStack,
        onBack = navigator::back,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider { registry.register(this, navigator) }
    )
}
