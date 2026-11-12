package io.github.sangcomz.commercenavigation.feature.order.domain

import io.github.sangcomz.commercenavigation.core.model.CartItem
import io.github.sangcomz.commercenavigation.core.model.Order

interface OrderRepository {
    fun place(items: List<CartItem>): Order
    fun find(orderId: String): Order?
}
