package com.sssmobile.baseApp.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.sssmobile.baseApp.theme.colors.ColorsProvider

@Composable
fun ThemeApp(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = remember(darkTheme) {
        ColorsProvider.getColorScheme(darkTheme)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TypographyDefault,
        shapes = ShapesDefault,
        content = content
    )
}
