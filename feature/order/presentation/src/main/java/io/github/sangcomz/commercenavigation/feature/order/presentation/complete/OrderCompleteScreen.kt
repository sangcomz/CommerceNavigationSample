package io.github.sangcomz.commercenavigation.feature.order.presentation.complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sangcomz.commercenavigation.core.ui.CommerceActionBar
import io.github.sangcomz.commercenavigation.core.ui.CommerceScreen
import io.github.sangcomz.commercenavigation.core.ui.EmptyState
import io.github.sangcomz.commercenavigation.core.ui.Eyebrow
import io.github.sangcomz.commercenavigation.core.ui.PrimaryAction
import io.github.sangcomz.commercenavigation.core.ui.ShopIcon
import io.github.sangcomz.commercenavigation.core.ui.ShopPanel
import io.github.sangcomz.commercenavigation.core.ui.ShopSymbol
import io.github.sangcomz.commercenavigation.core.ui.SummaryRow
import io.github.sangcomz.commercenavigation.core.ui.won

@Composable
fun OrderCompleteScreen(
    orderId: String,
    onHome: () -> Unit,
    onBack: () -> Unit,
    viewModel: OrderCompleteViewModel = hiltViewModel()
) {
    val order = viewModel.order(orderId)
    CommerceScreen("주문 완료", onBack, bottomBar = {
        CommerceActionBar { PrimaryAction("쇼핑 계속하기", onHome) }
    }) {
        if (order == null) {
            EmptyState(ShopSymbol.Receipt, "주문 정보를 찾을 수 없어요.", "다시 상품을 둘러보고 주문을 진행해 주세요.")
        } else {
            Column(Modifier.fillMaxWidth().padding(vertical = 24.dp), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)) {
                Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
                    ShopIcon(ShopSymbol.Check, modifier = Modifier.padding(28.dp).size(40.dp))
                }
                Eyebrow("A LITTLE JOY, ON ITS WAY")
                Text("기분 좋은 선택이에요", style = MaterialTheme.typography.headlineMedium)
                Text("샘플 주문이 완료됐어요.", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            ShopPanel {
                Text("나의 주문", style = MaterialTheme.typography.titleMedium)
                order.items.forEach { SummaryRow(it.product.name, "${it.quantity}개") }
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Text("총 ${won(order.total)}", style = MaterialTheme.typography.titleLarge)
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Eyebrow("ORDER NUMBER")
                Text("주문 번호: ${order.id}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("일상에 취향을 더해 주셔서 고마워요.", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
