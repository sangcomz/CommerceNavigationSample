package io.github.sangcomz.commercenavigation.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class ShopSymbol(val resourceId: Int) {
    Back(R.drawable.ic_back), Bag(R.drawable.ic_bag), Check(R.drawable.ic_check), Arrow(R.drawable.ic_arrow),
    Leaf(R.drawable.ic_leaf), Plus(R.drawable.ic_plus), Minus(R.drawable.ic_minus),
    Receipt(R.drawable.ic_receipt), Box(R.drawable.ic_box)
}

@Composable
fun ShopIcon(symbol: ShopSymbol, modifier: Modifier = Modifier, contentDescription: String? = null) {
    Icon(painterResource(symbol.resourceId), contentDescription, modifier.size(24.dp))
}

@Composable
fun ProductArtwork(productId: String, modifier: Modifier = Modifier) {
    val artwork = when (productId) {
        "bag-02" -> R.drawable.product_bag
        "lamp-03" -> R.drawable.product_lamp
        else -> R.drawable.product_mug
    }
    Image(painterResource(artwork), contentDescription = null, modifier = modifier, contentScale = ContentScale.Crop)
}

@Composable
fun Eyebrow(text: String) {
    Text(text, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
}

@Composable
fun ShopPanel(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Surface(modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp), content = content)
    }
}

@Composable
fun PrimaryAction(label: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Button(onClick = onClick, enabled = enabled, modifier = modifier.fillMaxWidth().defaultMinSize(minHeight = 56.dp), shape = RoundedCornerShape(14.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Spacer(Modifier.width(12.dp))
        ShopIcon(ShopSymbol.Arrow, modifier = Modifier.size(18.dp))
    }
}

@Composable
fun CommerceActionBar(content: @Composable ColumnScope.() -> Unit) {
    Surface(shadowElevation = 8.dp, color = MaterialTheme.colorScheme.surface) {
        Box(Modifier.fillMaxWidth().navigationBarsPadding(), contentAlignment = Alignment.Center) {
            Column(Modifier.widthIn(max = 840.dp).fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp), content = content)
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, strong: Boolean = false) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(Modifier.width(12.dp))
        Text(value, style = if (strong) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyLarge,
            fontWeight = if (strong) FontWeight.SemiBold else FontWeight.Normal)
    }
}

@Composable
fun EmptyState(symbol: ShopSymbol, title: String, message: String) {
    Column(Modifier.fillMaxWidth().padding(vertical = 36.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Surface(shape = CircleShape, color = MaterialTheme.colorScheme.primaryContainer) {
            ShopIcon(symbol, modifier = Modifier.padding(24.dp).size(30.dp))
        }
        Text(title, style = MaterialTheme.typography.titleLarge)
        Text(message, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
