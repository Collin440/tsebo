package io.github.collin440.tsebo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Tsebo",
    ) {
        App()
    }
}