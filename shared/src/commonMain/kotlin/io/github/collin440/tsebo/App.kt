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
                when {
                    initialState is Screen.Splash ->
                        (fadeIn(tween(600)) + slideInVertically(tween(600)) { it / 12 }) togetherWith
                                fadeOut(tween(400))
                    targetState is Screen.Section ->
                        slideInHorizontally(tween(350)) { it } togetherWith
                                slideOutHorizontally(tween(350)) { -it / 3 }
                    else ->
                        slideInHorizontally(tween(350)) { -it / 3 } togetherWith
                                slideOutHorizontally(tween(350)) { it }
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