package io.github.sangcomz.commercenavigation.feature.product.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sangcomz.commercenavigation.feature.product.domain.ProductRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ProductDataModule {
    @Binds
    abstract fun bindRepository(impl: SampleProductRepository): ProductRepository
}
