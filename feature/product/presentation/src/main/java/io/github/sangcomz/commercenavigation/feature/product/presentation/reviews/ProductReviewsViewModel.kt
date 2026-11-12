package io.github.sangcomz.commercenavigation.feature.product.presentation.reviews

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sangcomz.commercenavigation.feature.product.domain.GetProductReviewsUseCase
import javax.inject.Inject

@HiltViewModel
class ProductReviewsViewModel @Inject constructor(
    private val getReviews: GetProductReviewsUseCase
) : ViewModel() {
    fun reviews(productId: String) = getReviews(productId)
}
