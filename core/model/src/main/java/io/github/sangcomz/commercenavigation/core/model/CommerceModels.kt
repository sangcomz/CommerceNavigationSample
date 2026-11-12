package io.github.sangcomz.commercenavigation.core.model

data class Product(val id: String, val name: String, val price: Long, val description: String)

data class Review(val author: String, val content: String)

data class CartItem(val product: Product, val quantity: Int) {
    init { require(quantity > 0) }
    val subtotal: Long get() = product.price * quantity
}

data class Order(val id: String, val items: List<CartItem>) {
    val total: Long get() = items.sumOf { it.subtotal }
}
