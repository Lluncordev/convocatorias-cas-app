package com.dooofinance.convocatoriascas.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.dooofinance.convocatoriascas.src.utils.Typography
import com.dooofinance.convocatoriascas.src.utils._BACKGROUNG_PRIMAY_COLOR_DARK
import com.dooofinance.convocatoriascas.src.utils.LogoColorPrimary
import com.dooofinance.convocatoriascas.src.utils._BACKGROUNDCARDLIGHT
import com.dooofinance.convocatoriascas.src.utils._BACKGROUNG_PRIMAY_COLOR_LIGHT
import com.dooofinance.convocatoriascas.src.utils._BackgroundCardOscuro
import com.dooofinance.convocatoriascas.src.utils._SECUNDARY_TEXT_COLOR_LIGHT
import com.dooofinance.convocatoriascas.src.utils._SecundaryTextColor
import com.dooofinance.convocatoriascas.src.utils._TERTIARY_TEXT_COLO_LIGHT
import com.dooofinance.convocatoriascas.src.utils._TertiaryTextColor

private val LightColorScheme = lightColorScheme(
    background = _BACKGROUNG_PRIMAY_COLOR_LIGHT,
    primary = LogoColorPrimary,
    secondary = _SECUNDARY_TEXT_COLOR_LIGHT,
    tertiary = _TERTIARY_TEXT_COLO_LIGHT,
    surfaceContainer = _BACKGROUNDCARDLIGHT,
)

private val DarkColorScheme = darkColorScheme(
    background = _BACKGROUNG_PRIMAY_COLOR_DARK,
    primary = LogoColorPrimary,
    secondary = _SecundaryTextColor,
    tertiary = _TertiaryTextColor,
    surfaceContainer = _BackgroundCardOscuro,
)

@Composable
fun ConvocatoriasCasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else ->  LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}