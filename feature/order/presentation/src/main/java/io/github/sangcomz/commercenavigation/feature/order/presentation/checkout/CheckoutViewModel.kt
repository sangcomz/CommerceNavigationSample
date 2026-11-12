package io.github.sangcomz.commercenavigation.feature.order.presentation.checkout

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sangcomz.commercenavigation.feature.order.domain.ObserveCheckoutUseCase
import io.github.sangcomz.commercenavigation.feature.order.domain.PlaceOrderUseCase
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    observeCheckout: ObserveCheckoutUseCase,
    private val placeOrder: PlaceOrderUseCase
) : ViewModel() {
    val items = observeCheckout()
    fun place() = placeOrder()
}
