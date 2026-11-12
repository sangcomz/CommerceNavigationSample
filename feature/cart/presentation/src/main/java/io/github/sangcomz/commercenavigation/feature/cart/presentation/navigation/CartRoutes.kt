package io.github.sangcomz.commercenavigation.feature.cart.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey

@Serializable
data object CartRoute : NavigationRoute, NavKey
