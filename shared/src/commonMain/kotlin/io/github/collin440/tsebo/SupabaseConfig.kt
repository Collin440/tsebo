package io.github.collin440.tsebo

/** Connection settings for Supabase. The key must be the publishable (anon) key, never the secret one. */
data class SupabaseConfig(
    val url: String,
    val key: String,
) {
    val isConfigured: Boolean get() = url.isNotBlank() && key.isNotBlank()
}