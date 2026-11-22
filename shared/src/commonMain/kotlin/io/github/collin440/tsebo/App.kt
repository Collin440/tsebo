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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.coroutines.cancellation.CancellationException

private sealed interface Screen {
    data object Splash : Screen
    data object Contents : Screen
    data class Section(val entry: ContentsEntry) : Screen
}

@Composable
fun App(config: SupabaseConfig) {
    TseboTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.Splash) }
        var lastOpened by remember { mutableStateOf<ContentsEntry?>(null) }
        var hubsState by remember { mutableStateOf<HubsState>(HubsState.Loading) }
        var reloadCount by remember { mutableStateOf(0) }

        val repositoryResult = remember(config) {
            if (config.isConfigured) {
                runCatching { HubRepository(createTseboSupabaseClient(config)) }
            } else {
                null
            }
        }

        // Starts immediately, so hubs load while the splash plays.
        LaunchedEffect(repositoryResult, reloadCount) {
            hubsState = HubsState.Loading
            hubsState = when {
                repositoryResult == null ->
                    HubsState.Failed("Supabase keys are missing. Add supabase.url and supabase.key to local.properties.")
                repositoryResult.isFailure ->
                    HubsState.Failed(
                        repositoryResult.exceptionOrNull()?.message ?: "Could not start the Supabase client.",
                    )
                else -> try {
                    HubsState.Loaded(repositoryResult.getOrThrow().loadHubs())
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    HubsState.Failed(e.message ?: "Could not reach the server.")
                }
            }
        }

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
                    hubsState = hubsState,
                    onOpen = { entry ->
                        lastOpened = entry
                        screen = Screen.Section(entry)
                    },
                    onRetry = { reloadCount++ },
                )
                is Screen.Section -> SectionPlaceholder(current.entry, onBack = { screen = Screen.Contents })
            }
        }
    }
}