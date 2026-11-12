package io.github.sangcomz.commercenavigation.feature.cart.data

import io.github.sangcomz.commercenavigation.core.model.CartItem
import io.github.sangcomz.commercenavigation.core.model.Product
import io.github.sangcomz.commercenavigation.feature.cart.domain.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCartRepository @Inject constructor() : CartRepository {
    private val state = MutableStateFlow<List<CartItem>>(emptyList())
    override val items = state.asStateFlow()

    override fun add(product: Product) {
        state.update { current ->
            if (current.any { it.product.id == product.id }) {
                current.map { if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it }
            } else current + CartItem(product, 1)
        }
    }

    override fun changeQuantity(productId: String, delta: Int) {
        state.update { current ->
            current.mapNotNull { item ->
                if (item.product.id != productId) item
                else (item.quantity + delta).takeIf { it > 0 }?.let { item.copy(quantity = it) }
            }
        }
    }

    override fun clear() { state.value = emptyList() }
}
