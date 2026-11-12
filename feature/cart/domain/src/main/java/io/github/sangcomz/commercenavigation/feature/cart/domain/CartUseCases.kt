package io.github.sangcomz.commercenavigation.feature.cart.domain

import javax.inject.Inject

class ObserveCartUseCase @Inject constructor(private val repository: CartRepository) {
    operator fun invoke() = repository.items
}

class ChangeCartQuantityUseCase @Inject constructor(private val repository: CartRepository) {
    operator fun invoke(productId: String, delta: Int) = repository.changeQuantity(productId, delta)
}
