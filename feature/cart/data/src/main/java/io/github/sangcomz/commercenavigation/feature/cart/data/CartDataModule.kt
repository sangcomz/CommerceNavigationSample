package io.github.sangcomz.commercenavigation.feature.cart.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sangcomz.commercenavigation.feature.cart.domain.CartRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class CartDataModule {
    @Binds
    abstract fun bindRepository(impl: InMemoryCartRepository): CartRepository

    @Binds
    abstract fun bindCartController(impl: InMemoryCartRepository): io.github.sangcomz.commercenavigation.feature.common.cart.CartController
}
