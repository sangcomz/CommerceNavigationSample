package io.github.sangcomz.commercenavigation.feature.product.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sangcomz.commercenavigation.core.ui.CommerceActionBar
import io.github.sangcomz.commercenavigation.core.ui.CommerceScreen
import io.github.sangcomz.commercenavigation.core.ui.EmptyState
import io.github.sangcomz.commercenavigation.core.ui.Eyebrow
import io.github.sangcomz.commercenavigation.core.ui.PrimaryAction
import io.github.sangcomz.commercenavigation.core.ui.ProductArtwork
import io.github.sangcomz.commercenavigation.core.ui.ShopIcon
import io.github.sangcomz.commercenavigation.core.ui.ShopPanel
import io.github.sangcomz.commercenavigation.core.ui.ShopSymbol
import io.github.sangcomz.commercenavigation.core.ui.won

@Composable
fun ProductDetailScreen(
    productId: String,
    onOpenReviews: () -> Unit,
    onOpenCart: () -> Unit,
    onBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val product = viewModel.product(productId)
    var added by rememberSaveable { mutableStateOf(false) }
    CommerceScreen(
        title = "상품 상세",
        onBack = onBack,
        actions = { TextButton(onClick = onOpenCart) { Text("장바구니 보기", style = MaterialTheme.typography.bodyMedium) } },
        bottomBar = {
            if (product != null) CommerceActionBar {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(if (added) "장바구니에 담았어요." else "나의 일상에 더하기", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                    Text(won(product.price), style = MaterialTheme.typography.titleLarge)
                }
                PrimaryAction("장바구니 담기", { added = viewModel.addToCart(productId) })
            }
        }
    ) {
        if (product == null) {
            EmptyState(ShopSymbol.Box, "상품을 찾을 수 없어요.", "다른 상품을 만나보세요.")
        } else {
            ProductArtwork(productId, Modifier.fillMaxWidth().aspectRatio(1.08f).clip(RoundedCornerShape(24.dp)))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Eyebrow("MORROW. COLLECTION")
                Text(product.name, style = MaterialTheme.typography.headlineLarge)
                Text(product.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            ShopPanel {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ShopIcon(ShopSymbol.Leaf)
                    Text("작은 물건, 새로운 일상", style = MaterialTheme.typography.titleMedium)
                }
                Text("익숙한 공간에 자연스럽게 스며드는 물건을 골랐어요.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            TextButton(onClick = onOpenReviews, modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f).padding(vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("리뷰 보기", style = MaterialTheme.typography.titleMedium)
                    Text("함께 나누는 사용 경험", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                ShopIcon(ShopSymbol.Arrow)
            }
        }
    }
}
