package io.github.sangcomz.commercenavigation.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import io.github.sangcomz.commercenavigation.core.navigation.navigation3.Navigation3Navigator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class Navigation3NavigatorTest {
    private data object Home : NavigationRoute, NavKey
    private data class Detail(val id: String) : NavigationRoute, NavKey
    private data object Checkout : NavigationRoute, NavKey
    private data class Complete(val id: String) : NavigationRoute, NavKey

    @Test fun argumentsAndBackStackOrderArePreserved() {
        val stack = NavBackStack<NavKey>(Home)
        val navigator = Navigation3Navigator(stack)
        navigator.navigate(Detail("a"))
        navigator.navigate(Detail("a"))
        navigator.navigate(Detail("b"))
        assertEquals(listOf(Home, Detail("a"), Detail("b")), stack.toList())
        navigator.back()
        assertEquals(Detail("a"), stack.last())
    }

    @Test fun completionRemovesCheckoutAndKeepsRoot() {
        val stack = NavBackStack<NavKey>(Home, Detail("a"), Checkout)
        val navigator = Navigation3Navigator(stack)
        navigator.finishFlow(Complete("order-1"))
        assertEquals(listOf(Home, Complete("order-1")), stack.toList())
        navigator.back()
        navigator.back()
        assertEquals(listOf(Home), stack.toList())
    }

    @Test fun homeRemovesAllEntriesAboveRoot() {
        val stack = NavBackStack<NavKey>(Home, Detail("a"), Detail("b"))
        Navigation3Navigator(stack).home()
        assertEquals(listOf(Home), stack.toList())
    }
    @Test fun unsupportedRouteDoesNotClearExistingStack() {
        val stack = NavBackStack<NavKey>(Home, Detail("a"))
        val route = object : NavigationRoute {}
        assertThrows(IllegalArgumentException::class.java) { Navigation3Navigator(stack).finishFlow(route) }
        assertEquals(listOf(Home, Detail("a")), stack.toList())
    }
}
