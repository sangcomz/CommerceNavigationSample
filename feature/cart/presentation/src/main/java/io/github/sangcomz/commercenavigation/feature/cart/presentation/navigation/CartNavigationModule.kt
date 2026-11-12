package io.github.sangcomz.commercenavigation.feature.cart.presentation.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.feature.common.cart.CartRouteProvider

@Module
@InstallIn(SingletonComponent::class)
abstract class CartNavigationModule {
    @Binds
    abstract fun bindRouteProvider(impl: CartRouteProviderImpl): CartRouteProvider

    @Binds
    @IntoSet
    abstract fun bindEntryPointFactory(impl: CartEntryPointFactory): FeatureEntryPointFactory
}
