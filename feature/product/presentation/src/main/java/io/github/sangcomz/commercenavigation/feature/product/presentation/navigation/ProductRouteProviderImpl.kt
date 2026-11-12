package io.github.sangcomz.commercenavigation.feature.product.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import io.github.sangcomz.commercenavigation.feature.common.product.ProductRouteProvider
import javax.inject.Inject

class ProductRouteProviderImpl @Inject constructor() : ProductRouteProvider {
    override fun productListRoute(): NavigationRoute = ProductListRoute
    override fun productDetailRoute(productId: String): NavigationRoute = ProductDetailRoute(productId)
}
