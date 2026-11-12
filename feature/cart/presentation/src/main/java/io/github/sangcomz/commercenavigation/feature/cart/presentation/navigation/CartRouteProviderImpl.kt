package io.github.sangcomz.commercenavigation.feature.cart.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import io.github.sangcomz.commercenavigation.feature.common.cart.CartRouteProvider
import javax.inject.Inject

class CartRouteProviderImpl @Inject constructor() : CartRouteProvider {
    override fun cartRoute(): NavigationRoute = CartRoute
}
