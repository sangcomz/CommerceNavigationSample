package io.github.sangcomz.commercenavigation.feature.order.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPoint
import javax.inject.Inject
import io.github.sangcomz.commercenavigation.feature.order.presentation.checkout.CheckoutScreen
import io.github.sangcomz.commercenavigation.feature.order.presentation.complete.OrderCompleteScreen

class OrderEntryPointFactory @Inject constructor() : FeatureEntryPointFactory {
    override fun create(): List<FeatureEntryPoint> = listOf(
        { backStack ->
            entry<CheckoutRoute> {
                CheckoutScreen(
                    onOrderPlaced = { orderId ->
                        while (backStack.size > 1) backStack.removeLastOrNull()
                        backStack.add(OrderCompleteRoute(orderId))
                    },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        },
        { backStack ->
            entry<OrderCompleteRoute> { route ->
                OrderCompleteScreen(
                    orderId = route.orderId,
                    onHome = {
                        while (backStack.size > 1) backStack.removeLastOrNull()
                    },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
