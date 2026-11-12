package io.github.sangcomz.commercenavigation.feature.order.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.Navigator
import javax.inject.Inject
import io.github.sangcomz.commercenavigation.feature.order.presentation.checkout.CheckoutScreen
import io.github.sangcomz.commercenavigation.feature.order.presentation.complete.OrderCompleteScreen

class OrderEntryPointFactory @Inject constructor() : FeatureEntryPointFactory {
    override fun register(builder: EntryProviderScope<NavKey>, navigator: Navigator) {
        with(builder) {
            entry<CheckoutRoute> {
                CheckoutScreen(
                    onOrderPlaced = { navigator.finishFlow(OrderCompleteRoute(it)) },
                    onBack = navigator::back
                )
            }
            entry<OrderCompleteRoute> { route ->
                OrderCompleteScreen(
                    orderId = route.orderId,
                    onHome = navigator::home,
                    onBack = navigator::back
                )
            }
        }
    }
}
