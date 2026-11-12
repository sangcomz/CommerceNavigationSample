package io.github.sangcomz.commercenavigation.feature.product.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data object ProductListRoute : NavigationRoute

@Serializable
data class ProductDetailRoute(val productId: String) : NavigationRoute

@Serializable
internal data class ProductReviewsRoute(val productId: String) : NavigationRoute
