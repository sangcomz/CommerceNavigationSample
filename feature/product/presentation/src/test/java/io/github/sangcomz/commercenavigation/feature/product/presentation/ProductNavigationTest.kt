package io.github.sangcomz.commercenavigation.feature.product.presentation

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import io.github.sangcomz.commercenavigation.core.navigation.NavigationRoute
import io.github.sangcomz.commercenavigation.core.navigation.Navigator
import io.github.sangcomz.commercenavigation.feature.common.cart.CartRouteProvider
import io.github.sangcomz.commercenavigation.feature.product.presentation.navigation.ProductEntryPointFactory
import io.github.sangcomz.commercenavigation.feature.product.presentation.navigation.ProductRouteProviderImpl
import io.github.sangcomz.commercenavigation.feature.product.presentation.navigation.ProductDetailRoute
import io.github.sangcomz.commercenavigation.feature.product.presentation.navigation.ProductReviewsRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ProductNavigationTest {
    private data object Cart : NavigationRoute

    @Test fun oneRegistrationResolvesDifferentProductArguments() {
        val routes = ProductRouteProviderImpl()
        val navigator = object : Navigator {
            override fun navigate(route: NavigationRoute) = Unit
            override fun back() = Unit
            override fun finishFlow(route: NavigationRoute) = Unit
            override fun home() = Unit
        }
        val factory = ProductEntryPointFactory(object : CartRouteProvider {
            override fun cartRoute(): NavigationRoute = Cart
        })
        val provider = entryProvider<NavKey> { factory.register(this, navigator) }
        val first = routes.productDetailRoute("mug-01")
        val second = routes.productDetailRoute("bag-02")
        assertNotEquals(provider(first as NavKey).contentKey, provider(second as NavKey).contentKey)
        assertEquals(provider(first as NavKey).contentKey, provider(first as NavKey).contentKey)
        val reviews = ProductReviewsRoute("bag-02")
        assertNotEquals(provider(second as NavKey).contentKey, provider(reviews).contentKey)
    }

    @Test fun routeArgumentsSurviveSerialization() {
        val route = ProductDetailRoute("상품 / ? & 01")
        assertEquals(route, Json.decodeFromString<ProductDetailRoute>(Json.encodeToString(route)))
    }
}
