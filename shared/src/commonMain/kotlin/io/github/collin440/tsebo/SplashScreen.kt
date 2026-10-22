package io.github.collin440.tsebo

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val SplashBackground = Color(0xFF0D1117)
private val Teal = Color(0xFF14B8A6)
private val Muted = Color(0xFF9CA3AF)

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val name = "TSEBO"
    val tagline = "research. discover. engineer."

    val logoAlpha = remember { Animatable(0f) }
    val logoScale = remember { Animatable(0.8f) }
    val taglineAlpha = remember { Animatable(0f) }
    var typedCount by remember { mutableStateOf(0) }
    var cursorOn by remember { mutableStateOf(true) }

    // Blinking cursor
    LaunchedEffect(Unit) {
        while (true) {
            delay(450)
            cursorOn = !cursorOn
        }
    }

    // Main sequence (about 1.8 seconds)
    LaunchedEffect(Unit) {
        launch { logoScale.animateTo(1f, tween(500)) }
        logoAlpha.animateTo(1f, tween(500))
        for (i in 1..name.length) {
            typedCount = i
            delay(180)
        }
        taglineAlpha.animateTo(1f, tween(400))
        delay(900)
        onFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(SplashBackground),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.alpha(logoAlpha.value).scale(logoScale.value),
            ) {
                // The connection-point node above the tau
                Canvas(modifier = Modifier.size(28.dp)) {
                    drawCircle(color = Teal.copy(alpha = 0.25f), radius = size.minDimension / 2)
                    drawCircle(color = Teal, radius = size.minDimension / 5)
                }
                Text(
                    text = "τ",
                    color = Color.White,
                    fontSize = 96.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.height(24.dp))
            Text(
                text = name.take(typedCount) + if (cursorOn) "_" else " ",
                color = Color.White,
                fontSize = 28.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 6.sp,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = tagline,
                color = Muted,
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.alpha(taglineAlpha.value),
            )
        }
    }
}
