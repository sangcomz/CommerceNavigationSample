package io.github.sangcomz.commercenavigation.feature.order.presentation.complete

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sangcomz.commercenavigation.feature.order.domain.GetOrderUseCase
import javax.inject.Inject

@HiltViewModel
class OrderCompleteViewModel @Inject constructor(private val getOrder: GetOrderUseCase) : ViewModel() {
    fun order(orderId: String) = getOrder(orderId)
}
