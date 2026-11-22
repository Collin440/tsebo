package io.github.collin440.tsebo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.io.File
import java.util.Properties

private fun loadSupabaseConfig(): SupabaseConfig {
    val file = listOf(File("local.properties"), File("../local.properties"))
        .firstOrNull { it.exists() }
    val properties = Properties()
    file?.reader()?.use { properties.load(it) }
    return SupabaseConfig(
        url = properties.getProperty("supabase.url", ""),
        key = properties.getProperty("supabase.key", ""),
    )
}

fun main() {
    val config = loadSupabaseConfig()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Tsebo",
        ) {
            App(config)
        }
    }
}