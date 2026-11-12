package io.github.sangcomz.commercenavigation.feature.order.presentation.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.sangcomz.commercenavigation.core.ui.CommerceActionBar
import io.github.sangcomz.commercenavigation.core.ui.CommerceScreen
import io.github.sangcomz.commercenavigation.core.ui.EmptyState
import io.github.sangcomz.commercenavigation.core.ui.Eyebrow
import io.github.sangcomz.commercenavigation.core.ui.PrimaryAction
import io.github.sangcomz.commercenavigation.core.ui.ProductArtwork
import io.github.sangcomz.commercenavigation.core.ui.ShopIcon
import io.github.sangcomz.commercenavigation.core.ui.ShopPanel
import io.github.sangcomz.commercenavigation.core.ui.ShopSymbol
import io.github.sangcomz.commercenavigation.core.ui.SummaryRow
import io.github.sangcomz.commercenavigation.core.ui.won

@Composable
fun CheckoutScreen(
    onOrderPlaced: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val items by viewModel.items.collectAsStateWithLifecycle()
    val total = items.sumOf { it.subtotal }
    CommerceScreen("주문 확인", onBack, bottomBar = {
        CommerceActionBar {
            Text("주문 금액 ${won(total)}", style = MaterialTheme.typography.titleLarge)
            PrimaryAction("샘플 주문하기", { viewModel.place()?.let(onOrderPlaced) }, enabled = items.isNotEmpty())
        }
    }) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Eyebrow("ONE STEP CLOSER")
            Text("설레는 선택의 마지막", style = MaterialTheme.typography.headlineMedium)
            Text("담아둔 상품을 한 번 더 확인해 주세요.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (items.isEmpty()) EmptyState(ShopSymbol.Bag, "주문할 상품이 없어요.", "장바구니에 상품을 먼저 담아주세요.")
        if (items.isNotEmpty()) ShopPanel {
            Text("주문 상품", style = MaterialTheme.typography.titleMedium)
            items.forEach { item ->
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    ProductArtwork(item.product.id, Modifier.size(68.dp).clip(RoundedCornerShape(12.dp)))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(item.product.name, style = MaterialTheme.typography.titleMedium)
                        Text("${item.quantity}개", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Text(won(item.subtotal), style = MaterialTheme.typography.labelLarge)
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SummaryRow("총 상품 금액", won(total), strong = true)
        }
        Surface(shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.primaryContainer) {
            Row(Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                ShopIcon(ShopSymbol.Receipt)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("부담 없이 경험하는 샘플 주문", style = MaterialTheme.typography.titleMedium)
                    Text("샘플 주문이며 실제 결제나 배송은 발생하지 않습니다.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
