package io.github.sangcomz.commercenavigation.feature.cart.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.Navigator
import javax.inject.Inject
import io.github.sangcomz.commercenavigation.feature.common.product.ProductRouteProvider
import io.github.sangcomz.commercenavigation.feature.common.order.OrderRouteProvider
import io.github.sangcomz.commercenavigation.feature.cart.presentation.cart.CartScreen

class CartEntryPointFactory @Inject constructor(
    private val productRoutes: ProductRouteProvider,
    private val orderRoutes: OrderRouteProvider
) : FeatureEntryPointFactory {
    override fun register(builder: EntryProviderScope<NavKey>, navigator: Navigator) {
        with(builder) {
            entry<CartRoute> {
                CartScreen(
                    onOpenProduct = { navigator.navigate(productRoutes.productDetailRoute(it)) },
                    onCheckout = { navigator.navigate(orderRoutes.checkoutRoute()) },
                    onBack = navigator::back
                )
            }
        }
    }
}
