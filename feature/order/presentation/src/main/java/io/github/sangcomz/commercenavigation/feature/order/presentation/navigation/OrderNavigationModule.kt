package io.github.sangcomz.commercenavigation.feature.order.presentation.navigation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import io.github.sangcomz.commercenavigation.core.navigation.FeatureEntryPointFactory
import io.github.sangcomz.commercenavigation.feature.common.order.OrderRouteProvider

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderNavigationModule {
    @Binds
    abstract fun bindRouteProvider(impl: OrderRouteProviderImpl): OrderRouteProvider

    @Binds
    @IntoSet
    abstract fun bindEntryPointFactory(impl: OrderEntryPointFactory): FeatureEntryPointFactory
}
