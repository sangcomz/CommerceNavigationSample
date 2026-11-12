package io.github.sangcomz.commercenavigation.feature.product.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey

@Serializable
data object ProductListRoute : NavigationRoute, NavKey

@Serializable
data class ProductDetailRoute(val productId: String) : NavigationRoute, NavKey

@Serializable
internal data class ProductReviewsRoute(val productId: String) : NavigationRoute, NavKey
