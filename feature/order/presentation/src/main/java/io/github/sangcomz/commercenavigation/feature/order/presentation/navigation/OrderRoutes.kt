package io.github.sangcomz.commercenavigation.feature.order.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey

@Serializable
data object CheckoutRoute : NavigationRoute, NavKey

@Serializable
internal data class OrderCompleteRoute(val orderId: String) : NavigationRoute, NavKey
