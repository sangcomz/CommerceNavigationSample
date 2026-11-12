package io.github.sangcomz.commercenavigation.feature.common.product

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute

interface ProductRouteProvider {
    fun productListRoute(): NavigationRoute
    fun productDetailRoute(productId: String): NavigationRoute
}
