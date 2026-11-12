package io.github.sangcomz.commercenavigation.feature.product.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sangcomz.commercenavigation.feature.product.domain.GetProductsUseCase
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(getProducts: GetProductsUseCase) : ViewModel() {
    val products = getProducts()
}
