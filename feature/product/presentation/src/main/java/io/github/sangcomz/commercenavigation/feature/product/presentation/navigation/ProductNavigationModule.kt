package io.github.sangcomz.commercenavigation.feature.product.presentation.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.feature.common.product.ProductRouteProvider

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductNavigationModule {
    @Binds
    abstract fun bindRouteProvider(impl: ProductRouteProviderImpl): ProductRouteProvider

    @Binds
    @IntoSet
    abstract fun bindEntryPointFactory(impl: ProductEntryPointFactory): FeatureEntryPointFactory
}
