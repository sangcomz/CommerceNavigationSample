package io.github.sangcomz.commercenavigation.feature.product.presentation.navigation

import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPoint
import javax.inject.Inject
import io.github.sangcomz.commercenavigation.feature.common.cart.CartRouteProvider
import io.github.sangcomz.commercenavigation.feature.product.presentation.list.ProductListScreen
import io.github.sangcomz.commercenavigation.feature.product.presentation.detail.ProductDetailScreen
import io.github.sangcomz.commercenavigation.feature.product.presentation.reviews.ProductReviewsScreen

class ProductEntryPointFactory @Inject constructor(
    private val cartRoutes: CartRouteProvider
) : FeatureEntryPointFactory {
    override fun create(): List<FeatureEntryPoint> = listOf(
        { backStack ->
            entry<ProductListRoute> {
                ProductListScreen(
                    onOpenProduct = { backStack.add(ProductDetailRoute(it)) },
                    onOpenCart = { backStack.add(cartRoutes.cartRoute()) }
                )
            }
        },
        { backStack ->
            entry<ProductDetailRoute> { route ->
                ProductDetailScreen(
                    productId = route.productId,
                    onOpenReviews = { backStack.add(ProductReviewsRoute(route.productId)) },
                    onOpenCart = { backStack.add(cartRoutes.cartRoute()) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        },
        { backStack ->
            entry<ProductReviewsRoute> { route ->
                ProductReviewsScreen(
                    productId = route.productId,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
