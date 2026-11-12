package io.github.sangcomz.commercenavigation.feature.common.order

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute

interface OrderRouteProvider {
    fun checkoutRoute(): NavigationRoute
}
