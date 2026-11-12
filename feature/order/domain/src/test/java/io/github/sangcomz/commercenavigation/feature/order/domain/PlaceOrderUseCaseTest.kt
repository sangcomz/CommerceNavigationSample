package io.github.sangcomz.commercenavigation.feature.order.domain

import io.github.sangcomz.commercenavigation.core.model.CartItem
import io.github.sangcomz.commercenavigation.core.model.Product
import io.github.sangcomz.commercenavigation.core.model.Order
import io.github.sangcomz.commercenavigation.feature.common.cart.CartController
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaceOrderUseCaseTest {
    private class Cart : CartController {
        override val items = MutableStateFlow<List<CartItem>>(emptyList())
        override fun add(product: Product) { items.value = items.value + CartItem(product, 1) }
        override fun changeQuantity(productId: String, delta: Int) = Unit
        override fun clear() { items.value = emptyList() }
    }
    private class Orders : OrderRepository {
        var saved: Order? = null
        override fun place(items: List<CartItem>): Order = Order("order-1", items.toList()).also { saved = it }
        override fun find(orderId: String) = saved?.takeIf { it.id == orderId }
    }

    @Test fun emptyCartDoesNotCreateAnOrder() {
        val orders = Orders()
        assertNull(PlaceOrderUseCase(orders, Cart())())
        assertNull(orders.saved)
    }

    @Test fun orderKeepsItemsAfterCartIsClearedAndCannotBePlacedTwice() {
        val cart = Cart()
        cart.add(Product("mug", "머그", 18000, ""))
        val orders = Orders()
        val placeOrder = PlaceOrderUseCase(orders, cart)
        assertEquals("order-1", placeOrder())
        assertTrue(cart.items.value.isEmpty())
        assertEquals(18000L, orders.saved!!.total)
        assertEquals("mug", orders.saved!!.items.single().product.id)
        assertNull(placeOrder())
    }
}
