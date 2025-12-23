package com.sssmobile.baseApp.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.sssmobile.baseApp.theme.colors.mode.Dark
import com.sssmobile.baseApp.theme.colors.mode.Light

object ColorsProvider {
    fun getColorScheme(isDark: Boolean) =
        if (isDark) darkColorScheme(
            primary = Dark.Primary,
            secondary = Dark.Secondary,
            tertiary = Dark.Tertiary,
            background = Dark.Background,
            surface = Dark.Surface,
            surfaceVariant = Dark.SurfaceVariant,
            onBackground = Dark.TextPrimary,
            onSurface = Dark.TextPrimary,
            onSurfaceVariant = Dark.TextSecondary,
            outline = Dark.Outline
        )
        else lightColorScheme(
            primary = Light.Primary,
            secondary = Light.Secondary,
            tertiary = Light.Tertiary,
            background = Light.Background,
            surface = Light.Surface,
            surfaceVariant = Light.SurfaceVariant,
            onBackground = Light.TextPrimary,
            onSurface = Light.TextPrimary,
            onSurfaceVariant = Light.TextSecondary,
            outline = Light.Outline
        )
}