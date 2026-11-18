package io.github.collin440.tsebo

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

object Motion {
    const val Quick = 180
    const val Standard = 320
    const val Slow = 520

    // Ease-out: fast start, soft landing.
    val Smooth = CubicBezierEasing(0.2f, 0f, 0f, 1f)
}

/** Fades an element in and lifts it 12 dp into place, staggered by [index]. */
@Composable
fun Modifier.revealOnEnter(index: Int = 0, stepMs: Int = 70): Modifier {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(index * stepMs.toLong())
        progress.animateTo(1f, tween(Motion.Standard, easing = Motion.Smooth))
    }
    return this.graphicsLayer {
        alpha = progress.value
        translationY = (1f - progress.value) * 12.dp.toPx()
    }
}

/** Shrinks an element slightly while it is pressed. */
@Composable
fun Modifier.pressScale(interaction: InteractionSource, pressedScale: Float = 0.98f): Modifier {
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) pressedScale else 1f,
        animationSpec = tween(Motion.Quick, easing = Motion.Smooth),
        label = "pressScale",
    )
    return this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}