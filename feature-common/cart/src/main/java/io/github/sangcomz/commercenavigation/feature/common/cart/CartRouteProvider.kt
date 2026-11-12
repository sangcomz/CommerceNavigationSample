package io.github.sangcomz.commercenavigation.feature.common.cart

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute

interface CartRouteProvider {
    fun cartRoute(): NavigationRoute
}
