package io.github.sangcomz.commercenavigation.feature.product.domain

import io.github.sangcomz.commercenavigation.core.model.Product
import io.github.sangcomz.commercenavigation.core.model.Review

interface ProductRepository {
    fun products(): List<Product>
    fun product(id: String): Product?
    fun reviews(productId: String): List<Review>
}
