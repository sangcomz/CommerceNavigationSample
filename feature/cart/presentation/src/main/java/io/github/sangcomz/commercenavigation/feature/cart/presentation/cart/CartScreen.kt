package io.github.sangcomz.commercenavigation.feature.cart.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
fun CartScreen(
    onOpenProduct: (String) -> Unit,
    onCheckout: () -> Unit,
    onBack: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val items by viewModel.items.collectAsStateWithLifecycle()
    val total = items.sumOf { it.subtotal }
    CommerceScreen("장바구니", onBack, bottomBar = {
        CommerceActionBar {
            Text("합계 ${won(total)}", style = MaterialTheme.typography.titleLarge)
            PrimaryAction("주문 확인하기", onCheckout, enabled = items.isNotEmpty())
        }
    }) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Eyebrow("YOUR EVERYDAY PICKS")
            Text("좋아하는 것들을 담아", style = MaterialTheme.typography.headlineMedium)
            Text("${items.sumOf { it.quantity }}개의 물건이 당신을 기다려요.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        if (items.isEmpty()) EmptyState(ShopSymbol.Bag, "장바구니가 비어 있어요.", "마음에 드는 물건을 하나씩 담아보세요.")
        items.forEach { item ->
            ShopPanel {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    ProductArtwork(item.product.id, Modifier.size(88.dp, 106.dp).clip(RoundedCornerShape(14.dp)))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        TextButton(onClick = { onOpenProduct(item.product.id) }) {
                            Text(item.product.name, style = MaterialTheme.typography.titleMedium)
                        }
                        Text(won(item.subtotal), style = MaterialTheme.typography.titleMedium)
                        Text("morrow. collection", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Row(Modifier.fillMaxWidth().heightIn(min = 48.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("수량", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    OutlinedIconButton(onClick = { viewModel.change(item.product.id, -1) }) {
                        ShopIcon(ShopSymbol.Minus, modifier = Modifier.size(18.dp), contentDescription = "${item.product.name} 수량 줄이기")
                    }
                    Text("${item.quantity}개", modifier = Modifier.semantics { contentDescription = "${item.product.name} 수량 ${item.quantity}개" }, style = MaterialTheme.typography.titleMedium)
                    OutlinedIconButton(onClick = { viewModel.change(item.product.id, 1) }) {
                        ShopIcon(ShopSymbol.Plus, modifier = Modifier.size(18.dp), contentDescription = "${item.product.name} 수량 늘리기")
                    }
                }
            }
        }
        if (items.isNotEmpty()) ShopPanel {
            Text("주문 요약", style = MaterialTheme.typography.titleMedium)
            SummaryRow("상품 수량", "${items.sumOf { it.quantity }}개")
            SummaryRow("상품 금액", won(total), strong = true)
            Text("다음 화면에서 샘플 주문을 확인할 수 있어요.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
