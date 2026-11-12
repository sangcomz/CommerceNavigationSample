package io.github.sangcomz.commercenavigation.feature.order.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import io.github.sangcomz.commercenavigation.feature.common.order.OrderRouteProvider
import javax.inject.Inject

class OrderRouteProviderImpl @Inject constructor() : OrderRouteProvider {
    override fun checkoutRoute(): NavigationRoute = CheckoutRoute
}
