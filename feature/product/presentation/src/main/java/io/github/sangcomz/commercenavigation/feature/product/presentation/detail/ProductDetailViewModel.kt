package io.github.sangcomz.commercenavigation.feature.product.presentation.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sangcomz.commercenavigation.feature.product.domain.GetProductUseCase
import io.github.sangcomz.commercenavigation.feature.product.domain.AddProductToCartUseCase
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProduct: GetProductUseCase,
    private val addProduct: AddProductToCartUseCase
) : ViewModel() {
    fun product(productId: String) = getProduct(productId)
    fun addToCart(productId: String) = addProduct(productId)
}
