package io.github.sangcomz.commercenavigation.feature.order.domain

import io.github.sangcomz.commercenavigation.feature.common.cart.CartController
import javax.inject.Inject

class ObserveCheckoutUseCase @Inject constructor(private val cart: CartController) {
    operator fun invoke() = cart.items
}

class PlaceOrderUseCase @Inject constructor(
    private val repository: OrderRepository,
    private val cart: CartController
) {
    operator fun invoke(): String? {
        val items = cart.items.value.toList()
        if (items.isEmpty()) return null
        val order = repository.place(items)
        cart.clear()
        return order.id
    }
}

class GetOrderUseCase @Inject constructor(private val repository: OrderRepository) {
    operator fun invoke(orderId: String) = repository.find(orderId)
}
