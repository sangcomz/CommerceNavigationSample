package io.github.sangcomz.commercenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import io.github.sangcomz.commercenavigation.core.ui.CommerceTheme
import io.github.sangcomz.commercenavigation.feature.common.product.ProductRouteProvider
import io.github.sangcomz.commercenavigation.navigation.AppNavHost
import io.github.sangcomz.commercenavigation.navigation.NavigationEntryPointRegistry
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var productRoutes: ProductRouteProvider
    @Inject lateinit var entryPoints: NavigationEntryPointRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CommerceTheme {
                AppNavHost(productRoutes.productListRoute(), entryPoints)
            }
        }
    }
}
