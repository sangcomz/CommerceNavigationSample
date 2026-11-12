package io.github.sangcomz.commercenavigation.feature.order.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data object CheckoutRoute : NavigationRoute

@Serializable
internal data class OrderCompleteRoute(val orderId: String) : NavigationRoute
