package io.github.collin440.tsebo

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class HubRepository(private val client: SupabaseClient) {
    suspend fun loadHubs(): List<HubSummary> =
        client.from("hub_summaries")
            .select {
                order(column = "position", order = Order.ASCENDING)
            }
            .decodeList<HubSummary>()
}