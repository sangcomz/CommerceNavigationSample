package io.github.sangcomz.commercenavigation.feature.product.presentation.reviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sangcomz.commercenavigation.core.ui.CommerceScreen
import io.github.sangcomz.commercenavigation.core.ui.EmptyState
import io.github.sangcomz.commercenavigation.core.ui.Eyebrow
import io.github.sangcomz.commercenavigation.core.ui.ShopPanel
import io.github.sangcomz.commercenavigation.core.ui.ShopSymbol

@Composable
fun ProductReviewsScreen(
    productId: String,
    onBack: () -> Unit,
    viewModel: ProductReviewsViewModel = hiltViewModel()
) {
    val reviews = viewModel.reviews(productId)
    CommerceScreen("상품 리뷰", onBack) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Eyebrow("EVERYDAY STORIES")
            Text("일상에서 만난 이야기", style = MaterialTheme.typography.headlineMedium)
            Text("${reviews.size}개의 사용 경험을 만나보세요.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (reviews.isEmpty()) EmptyState(ShopSymbol.Leaf, "등록된 리뷰가 없어요.", "새로운 이야기를 기다리고 있어요.")
        reviews.forEach { review ->
            ShopPanel {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
                        Text(review.author.take(1), modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), style = MaterialTheme.typography.titleMedium)
                    }
                    Column {
                        Text(review.author, style = MaterialTheme.typography.titleMedium)
                        Text("morrow. customer", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                Text(review.content, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
