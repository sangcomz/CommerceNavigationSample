package io.github.sangcomz.commercenavigation.feature.product.domain

import io.github.sangcomz.commercenavigation.feature.common.cart.CartController
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke() = repository.products()
}

class GetProductUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke(productId: String) = repository.product(productId)
}

class GetProductReviewsUseCase @Inject constructor(private val repository: ProductRepository) {
    operator fun invoke(productId: String) = repository.reviews(productId)
}

class AddProductToCartUseCase @Inject constructor(
    private val repository: ProductRepository,
    private val cart: CartController
) {
    operator fun invoke(productId: String): Boolean {
        val product = repository.product(productId) ?: return false
        cart.add(product)
        return true
    }
}
