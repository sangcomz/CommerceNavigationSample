package io.github.sangcomz.commercenavigation.core.navigation.navigation3

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import io.github.sangcomz.commercenavigation.core.navigation.Navigator

class Navigation3Navigator(private val backStack: NavBackStack<NavKey>) : Navigator {
    override fun navigate(route: NavigationRoute) {
        val key = route.navKey()
        if (backStack.lastOrNull() != key) backStack.add(key)
    }

    override fun back() {
        if (backStack.size > 1) backStack.removeLastOrNull()
    }

    override fun finishFlow(route: NavigationRoute) {
        val key = route.navKey()
        while (backStack.size > 1) backStack.removeLastOrNull()
        backStack.add(key)
    }

    override fun home() {
        while (backStack.size > 1) backStack.removeLastOrNull()
    }

    private fun NavigationRoute.navKey(): NavKey {
        require(this is NavKey) { "Navigation 3 requires a NavKey route: $this" }
        return this
    }
}
