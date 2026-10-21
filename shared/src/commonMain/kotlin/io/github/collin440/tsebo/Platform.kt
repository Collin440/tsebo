package io.github.collin440.tsebo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform