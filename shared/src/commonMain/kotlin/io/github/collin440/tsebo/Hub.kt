package io.github.collin440.tsebo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HubSummary(
    val id: String,
    val position: Int,
    val title: String,
    @SerialName("item_count") val itemCount: Int,
)

sealed interface HubsState {
    data object Loading : HubsState
    data class Loaded(val hubs: List<HubSummary>) : HubsState
    data class Failed(val reason: String) : HubsState
}