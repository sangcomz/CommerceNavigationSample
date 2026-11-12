package io.github.sangcomz.commercenavigation.feature.order.data

import io.github.sangcomz.commercenavigation.core.model.CartItem
import io.github.sangcomz.commercenavigation.core.model.Order
import io.github.sangcomz.commercenavigation.feature.order.domain.OrderRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryOrderRepository @Inject constructor() : OrderRepository {
    private val orders = mutableMapOf<String, Order>()
    override fun place(items: List<CartItem>): Order {
        require(items.isNotEmpty())
        val order = Order(UUID.randomUUID().toString(), items.toList())
        orders[order.id] = order
        return order
    }
    override fun find(orderId: String) = orders[orderId]
}
