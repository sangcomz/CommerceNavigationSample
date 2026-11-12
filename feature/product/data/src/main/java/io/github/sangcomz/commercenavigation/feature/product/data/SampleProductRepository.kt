package io.github.sangcomz.commercenavigation.feature.product.data

import io.github.sangcomz.commercenavigation.core.model.Product
import io.github.sangcomz.commercenavigation.core.model.Review
import io.github.sangcomz.commercenavigation.feature.product.domain.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleProductRepository @Inject constructor() : ProductRepository {
    private val catalog = listOf(
        Product("mug-01", "데일리 머그", 18000, "따뜻한 하루를 시작하는 세라믹 머그입니다."),
        Product("bag-02", "캔버스 토트백", 32000, "가벼운 외출에 어울리는 넉넉한 토트백입니다."),
        Product("lamp-03", "포인트 조명", 59000, "책상 위에 은은한 빛을 더하는 조명입니다.")
    )
    override fun products() = catalog
    override fun product(id: String) = catalog.find { it.id == id }
    override fun reviews(productId: String): List<Review> = if (product(productId) == null) emptyList() else listOf(
        Review("지수", "사진처럼 예쁘고 매일 쓰기 좋아요."),
        Review("민호", "마감이 깔끔하고 만족스러워요.")
    )
}
