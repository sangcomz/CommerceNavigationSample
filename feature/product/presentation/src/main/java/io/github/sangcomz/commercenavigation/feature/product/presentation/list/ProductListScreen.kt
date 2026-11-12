package io.github.sangcomz.commercenavigation.feature.product.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.sangcomz.commercenavigation.core.ui.CommerceScreen
import io.github.sangcomz.commercenavigation.core.ui.Eyebrow
import io.github.sangcomz.commercenavigation.core.ui.ProductArtwork
import io.github.sangcomz.commercenavigation.core.ui.ShopIcon
import io.github.sangcomz.commercenavigation.core.ui.ShopSymbol
import io.github.sangcomz.commercenavigation.core.ui.won

@Composable
fun ProductListScreen(
    onOpenProduct: (String) -> Unit,
    onOpenCart: () -> Unit,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    CommerceScreen(
        title = "상품 목록",
        brand = true,
        actions = { IconButton(onClick = onOpenCart) { ShopIcon(ShopSymbol.Bag, contentDescription = "장바구니 보기") } }
    ) {
        Surface(shape = RoundedCornerShape(24.dp), color = MaterialTheme.colorScheme.secondaryContainer) {
            Row(Modifier.fillMaxWidth().heightIn(min = 220.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f).padding(start = 24.dp, top = 24.dp, bottom = 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("THE EVERYDAY EDIT", style = MaterialTheme.typography.labelSmall)
                    Text("일상에\n취향을 더하다", style = MaterialTheme.typography.headlineMedium)
                    Text("오래 곁에 두고 싶은\n작은 물건들을 모았어요.", style = MaterialTheme.typography.bodyMedium)
                }
                ProductArtwork("mug-01", Modifier.weight(0.9f).height(240.dp).clip(RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp)))
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Eyebrow("CURATED FOR YOU")
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("당신을 위한 발견", style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f))
                Text("${viewModel.products.size} items", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text("매일의 장면을 바꾸는, 기분 좋은 선택.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        BoxWithConstraints {
            val columns = if (maxWidth >= 600.dp) 3 else if (maxWidth >= 300.dp) 2 else 1
            Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                viewModel.products.chunked(columns).forEach { products ->
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        products.forEach { product ->
                            Surface(onClick = { onOpenProduct(product.id) }, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(0.dp)) {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Box {
                                        ProductArtwork(product.id, Modifier.fillMaxWidth().aspectRatio(0.93f).clip(RoundedCornerShape(18.dp)))
                                        Surface(Modifier.padding(10.dp), shape = RoundedCornerShape(6.dp), color = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f)) {
                                            Text("SELECTED", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), style = MaterialTheme.typography.labelSmall)
                                        }
                                    }
                                    Text(product.name, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                    Text(won(product.price), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Text("morrow. collection", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                        repeat(columns - products.size) { Spacer(Modifier.weight(1f)) }
                    }
                }
            }
        }
        Surface(shape = RoundedCornerShape(18.dp), color = MaterialTheme.colorScheme.primaryContainer) {
            Row(Modifier.fillMaxWidth().padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                ShopIcon(ShopSymbol.Leaf)
                Spacer(Modifier.width(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("덜어내고, 좋아하는 것만.", style = MaterialTheme.typography.titleMedium)
                    Text("당신의 일상에 오래 머무는 선택", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        OutlinedButton(onClick = onOpenCart, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
            Text("장바구니 보기", modifier = Modifier.padding(8.dp))
        }
    }
}
