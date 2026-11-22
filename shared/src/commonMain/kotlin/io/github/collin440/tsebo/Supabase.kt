package io.github.collin440.tsebo

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

fun createTseboSupabaseClient(config: SupabaseConfig): SupabaseClient =
    createSupabaseClient(
        supabaseUrl = config.url,
        supabaseKey = config.key,
    ) {
        install(Postgrest)
    }