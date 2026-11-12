package io.github.sangcomz.commercenavigation.feature.cart.data

import io.github.sangcomz.commercenavigation.core.model.Product
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class InMemoryCartRepositoryTest {
    private val mug = Product("mug", "머그", 18000, "")

    @Test fun addingSameProductIncrementsExistingLine() {
        val repository = InMemoryCartRepository()
        repository.add(mug)
        repository.add(mug)
        assertEquals(1, repository.items.value.size)
        assertEquals(2, repository.items.value.single().quantity)
        assertEquals(36000L, repository.items.value.single().subtotal)
    }

    @Test fun reducingQuantityToZeroRemovesTheLine() {
        val repository = InMemoryCartRepository()
        repository.add(mug)
        repository.changeQuantity(mug.id, -1)
        assertTrue(repository.items.value.isEmpty())
    }
}
