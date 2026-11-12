package io.github.sangcomz.commercenavigation.feature.cart.presentation.cart

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sangcomz.commercenavigation.feature.cart.domain.ObserveCartUseCase
import io.github.sangcomz.commercenavigation.feature.cart.domain.ChangeCartQuantityUseCase
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    observeCart: ObserveCartUseCase,
    private val changeQuantity: ChangeCartQuantityUseCase
) : ViewModel() {
    val items = observeCart()
    fun change(productId: String, delta: Int) = changeQuantity(productId, delta)
}
