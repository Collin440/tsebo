package io.github.collin440.tsebo

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object TseboColors {
    val Background = Color(0xFF0D1117)
    val Surface = Color(0xFF161B22)
    val Text = Color(0xFFE6EDF3)
    val Muted = Color(0xFF8B949E)
    val Purple = Color(0xFF7F52FF)
    val Teal = Color(0xFF14B8A6)
    val Error = Color(0xFFF85149)
}

private val TseboDarkColors = darkColorScheme(
    primary = TseboColors.Purple,
    secondary = TseboColors.Teal,
    background = TseboColors.Background,
    surface = TseboColors.Surface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TseboColors.Text,
    onSurface = TseboColors.Text,
)

@Composable
fun TseboTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = TseboDarkColors, content = content)
}