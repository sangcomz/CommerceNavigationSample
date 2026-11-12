package io.github.sangcomz.commercenavigation.feature.product.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.Navigator
import javax.inject.Inject
import io.github.sangcomz.commercenavigation.feature.common.cart.CartRouteProvider
import io.github.sangcomz.commercenavigation.feature.product.presentation.list.ProductListScreen
import io.github.sangcomz.commercenavigation.feature.product.presentation.detail.ProductDetailScreen
import io.github.sangcomz.commercenavigation.feature.product.presentation.reviews.ProductReviewsScreen

class ProductEntryPointFactory @Inject constructor(
    private val cartRoutes: CartRouteProvider
) : FeatureEntryPointFactory {
    override fun register(builder: EntryProviderScope<NavKey>, navigator: Navigator) {
        with(builder) {
            entry<ProductListRoute> {
                ProductListScreen(
                    onOpenProduct = { navigator.navigate(ProductDetailRoute(it)) },
                    onOpenCart = { navigator.navigate(cartRoutes.cartRoute()) }
                )
            }
            entry<ProductDetailRoute> { route ->
                ProductDetailScreen(
                    productId = route.productId,
                    onOpenReviews = { navigator.navigate(ProductReviewsRoute(route.productId)) },
                    onOpenCart = { navigator.navigate(cartRoutes.cartRoute()) },
                    onBack = navigator::back
                )
            }
            entry<ProductReviewsRoute> { route ->
                ProductReviewsScreen(productId = route.productId, onBack = navigator::back)
            }
        }
    }
}
