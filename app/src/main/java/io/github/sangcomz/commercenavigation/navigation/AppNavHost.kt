package io.github.sangcomz.commercenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute

@Composable
fun AppNavHost(startRoute: NavigationRoute, registry: NavigationEntryPointRegistry) {
    val backStack = rememberNavBackStack(startRoute)
    val entries = remember(registry) { registry.entries() }
    NavDisplay(
        backStack = backStack,
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entries.forEach { entry -> entry(this, backStack) }
        }
    )
}
