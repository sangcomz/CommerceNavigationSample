package io.github.sangcomz.commercenavigation.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val LightColors = lightColorScheme(
    primary = Color(0xFF214C3D), onPrimary = Color.White,
    primaryContainer = Color(0xFFE5EDE3), onPrimaryContainer = Color(0xFF214C3D),
    secondary = Color(0xFF80684D), secondaryContainer = Color(0xFFF0E7D9),
    onSecondaryContainer = Color(0xFF55432F),
    background = Color(0xFFF8F8F4), onBackground = Color(0xFF25342B),
    surface = Color(0xFFFFFFFF), onSurface = Color(0xFF25342B),
    surfaceVariant = Color(0xFFF0F1EA), onSurfaceVariant = Color(0xFF70786F),
    outline = Color(0xFFD9DED4), outlineVariant = Color(0xFFE8EBE3)
)
private val DarkColors = darkColorScheme(
    primary = Color(0xFFBED8BE), onPrimary = Color(0xFF193629),
    primaryContainer = Color(0xFF2E4534), onPrimaryContainer = Color(0xFFDCEAD7),
    secondary = Color(0xFFD9C4A4), secondaryContainer = Color(0xFF41392D),
    onSecondaryContainer = Color(0xFFF1E4D1),
    background = Color(0xFF161E19), onBackground = Color(0xFFE3E9DD),
    surface = Color(0xFF202B23), onSurface = Color(0xFFE3E9DD),
    surfaceVariant = Color(0xFF2B382D), onSurfaceVariant = Color(0xFFB1BAAC),
    outline = Color(0xFF50604E), outlineVariant = Color(0xFF354235)
)
private val ShopTypography = Typography(
    headlineLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 30.sp, lineHeight = 40.sp, letterSpacing = (-0.8).sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 26.sp, lineHeight = 36.sp, letterSpacing = (-0.6).sp),
    titleLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 21.sp, lineHeight = 30.sp),
    titleMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 16.sp, lineHeight = 24.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 15.sp, lineHeight = 25.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 13.sp, lineHeight = 21.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, lineHeight = 20.sp),
    labelSmall = TextStyle(fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Medium, fontSize = 10.sp, lineHeight = 16.sp, letterSpacing = 1.sp)
)

@Composable
fun CommerceTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors, typography = ShopTypography, content = content)
}
