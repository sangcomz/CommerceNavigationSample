package io.github.sangcomz.commercenavigation.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

fun won(value: Long): String = NumberFormat.getIntegerInstance(Locale.KOREA).format(value) + "원"

@Composable
fun CommerceScreen(
    title: String,
    onBack: (() -> Unit)? = null,
    brand: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Surface(color = MaterialTheme.colorScheme.background) {
                Box(Modifier.fillMaxWidth().statusBarsPadding(), contentAlignment = Alignment.Center) {
                    Row(
                        Modifier.widthIn(max = 840.dp).fillMaxWidth().heightIn(min = 76.dp).padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (onBack != null) IconButton(onClick = onBack) { ShopIcon(ShopSymbol.Back, contentDescription = "뒤로") }
                        Column(Modifier.weight(1f).padding(start = if (brand) 8.dp else 0.dp)) {
                            if (brand) Text("morrow.", fontFamily = FontFamily.Serif, fontSize = 34.sp, letterSpacing = (-1.5).sp)
                            Text(title, style = if (brand) MaterialTheme.typography.labelSmall else MaterialTheme.typography.titleMedium,
                                color = if (brand) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface)
                        }
                        actions()
                    }
                    }
            }
        },
        bottomBar = bottomBar
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.TopCenter) {
            Column(
                Modifier.widthIn(max = 840.dp).fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 24.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                content = content
            )
        }
    }
}
