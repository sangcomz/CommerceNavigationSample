package io.github.sangcomz.commercenavigation.feature.order.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sangcomz.commercenavigation.feature.order.domain.OrderRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderDataModule {
    @Binds
    abstract fun bindRepository(impl: InMemoryOrderRepository): OrderRepository
}
