package io.github.sangcomz.commercenavigation.feature.cart.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPoint
import javax.inject.Inject
import io.github.sangcomz.commercenavigation.feature.common.product.ProductRouteProvider
import io.github.sangcomz.commercenavigation.feature.common.order.OrderRouteProvider
import io.github.sangcomz.commercenavigation.feature.cart.presentation.cart.CartScreen

class CartEntryPointFactory @Inject constructor(
    private val productRoutes: ProductRouteProvider,
    private val orderRoutes: OrderRouteProvider
) : FeatureEntryPointFactory {
    override fun create(): List<FeatureEntryPoint> = listOf(
        { backStack ->
            entry<CartRoute> {
                CartScreen(
                    onOpenProduct = { backStack.add(productRoutes.productDetailRoute(it)) },
                    onCheckout = { backStack.add(orderRoutes.checkoutRoute()) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
