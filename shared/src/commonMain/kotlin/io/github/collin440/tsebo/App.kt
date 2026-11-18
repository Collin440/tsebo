package io.github.collin440.tsebo

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

private sealed interface Screen {
    data object Splash : Screen
    data object Contents : Screen
    data class Section(val entry: ContentsEntry) : Screen
}

@Composable
fun App() {
    TseboTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.Splash) }
        var lastOpened by remember { mutableStateOf<ContentsEntry?>(null) }

        AnimatedContent(
            targetState = screen,
            transitionSpec = {
                val ease = Motion.Smooth
                when {
                    initialState is Screen.Splash ->
                        (fadeIn(tween(Motion.Slow, easing = ease)) +
                                slideInVertically(tween(Motion.Slow, easing = ease)) { it / 14 }) togetherWith
                                fadeOut(tween(Motion.Standard, easing = ease))
                    targetState is Screen.Section ->
                        (fadeIn(tween(Motion.Standard, easing = ease)) +
                                slideInHorizontally(tween(Motion.Standard, easing = ease)) { it / 5 }) togetherWith
                                (fadeOut(tween(Motion.Quick, easing = ease)) +
                                        slideOutHorizontally(tween(Motion.Standard, easing = ease)) { -it / 8 })
                    else ->
                        (fadeIn(tween(Motion.Standard, easing = ease)) +
                                slideInHorizontally(tween(Motion.Standard, easing = ease)) { -it / 8 }) togetherWith
                                (fadeOut(tween(Motion.Quick, easing = ease)) +
                                        slideOutHorizontally(tween(Motion.Standard, easing = ease)) { it / 5 })
                }
            },
            label = "screens",
        ) { current ->
            when (current) {
                Screen.Splash -> SplashScreen(onFinished = { screen = Screen.Contents })
                Screen.Contents -> ContentsScreen(
                    lastOpened = lastOpened,
                    onOpen = { entry ->
                        lastOpened = entry
                        screen = Screen.Section(entry)
                    },
                )
                is Screen.Section -> SectionPlaceholder(current.entry, onBack = { screen = Screen.Contents })
            }
        }
    }
}