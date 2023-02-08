package ru.cyclone.cycnote.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

private val DarkColorPalette = darkColors(
    primary = floating,
    primaryVariant = cardDark,
    secondary = floating,
    background = backgroundDarkColor,
    surface = dialogTextDark
)

private val LightColorPalette = lightColors(
    primary = floating,
    primaryVariant = cardLight,
    secondary = floating,
    background = backgroundLightColor,
    surface = dialogTextLight
)

private val DarkTypography = Typography(
    h6 = TextStyle(
        color = dialogTextDark,
        fontSize = 20.sp
    )
)

private val LightTypography = Typography(
    h6 = TextStyle(
        color = dialogTextLight,
        fontSize = 20.sp
    )
)



@Composable
fun CycNoteTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val typography = if (darkTheme) DarkTypography else LightTypography

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}