package io.github.sangcomz.commercenavigation.feature.common.cart

import io.github.sangcomz.commercenavigation.core.model.CartItem
import io.github.sangcomz.commercenavigation.core.model.Product
import kotlinx.coroutines.flow.StateFlow

interface CartController {
    val items: StateFlow<List<CartItem>>
    fun add(product: Product)
    fun changeQuantity(productId: String, delta: Int)
    fun clear()
}
