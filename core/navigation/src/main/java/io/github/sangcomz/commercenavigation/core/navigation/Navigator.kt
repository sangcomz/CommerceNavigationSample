package io.github.sangcomz.commercenavigation.core.navigation

interface Navigator {
    fun navigate(route: NavigationRoute)
    fun back()
    fun finishFlow(route: NavigationRoute)
    fun home()
}
