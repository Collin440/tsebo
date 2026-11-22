package io.github.collin440.tsebo

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateColorAsState

private val CBackground = TseboColors.Background
private val CText = TseboColors.Text
private val CMuted = TseboColors.Muted
private val CPurple = TseboColors.Purple
private val CTeal = TseboColors.Teal
private val CError = TseboColors.Error

data class ContentsEntry(
    val id: String,
    val number: String,
    val title: String,
    val status: String,
    val indent: Int = 0,
)

private fun buildEntries(state: HubsState): List<ContentsEntry> {
    val hubs = (state as? HubsState.Loaded)?.hubs.orEmpty()
    val briefStatus = when (state) {
        is HubsState.Loaded -> "${hubs.sumOf { it.itemCount }} items"
        HubsState.Loading -> "loading"
        is HubsState.Failed -> "offline"
    }
    val hubsStatus = when (state) {
        is HubsState.Loaded -> "${hubs.size}"
        HubsState.Loading -> "loading"
        is HubsState.Failed -> "offline"
    }
    return buildList {
        add(ContentsEntry("brief", "1", "The Brief", briefStatus))
        add(ContentsEntry("hubs", "2", "Hubs", hubsStatus))
        hubs.forEachIndexed { index, hub ->
            add(ContentsEntry("hub-${hub.id}", "2.${index + 1}", hub.title, "${hub.itemCount}", indent = 1))
        }
        add(ContentsEntry("build-logs", "3", "Build Logs", "soon"))
        add(ContentsEntry("events", "4", "Events", "soon"))
        add(ContentsEntry("lens", "5", "Your lens", "soon"))
    }
}

@Composable
fun ContentsScreen(
    lastOpened: ContentsEntry?,
    hubsState: HubsState,
    onOpen: (ContentsEntry) -> Unit,
    onRetry: () -> Unit,
) {
    val entries = remember(hubsState) { buildEntries(hubsState) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CBackground)
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Text(
            "TSEBO",
            color = CText,
            fontSize = 22.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.pointerInput(Unit) {

            },
        )
        val showVersion = false
        if (showVersion) {
            Text(
                "v0.1.0 pre-alpha | built with KMP",
                color = CTeal,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
            )
        }
        Text("research. discover. engineer.", color = CMuted, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
        Text("Today's edition", color = CTeal, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
        if (hubsState is HubsState.Failed) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "! ${hubsState.reason}",
                color = CError,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
            )
            Text(
                text = "> retry",
                color = CPurple,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.clickable(onClick = onRetry).padding(vertical = 6.dp),
            )
        }
        if (lastOpened != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "> resume: ${lastOpened.number} ${lastOpened.title}",
                color = CPurple,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.clickable { onOpen(lastOpened) },
            )
        }
        Spacer(Modifier.height(24.dp))
        entries.forEachIndexed { index, entry ->
            ContentsRow(entry = entry, index = index, onClick = { onOpen(entry) })
        }
        Spacer(Modifier.height(32.dp))
        Text("That's everything. Go build something.", color = CMuted, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
    }
}

@Composable
private fun ContentsRow(entry: ContentsEntry, index: Int, onClick: () -> Unit) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val titleColor by animateColorAsState(
        targetValue = if (pressed) CPurple else CText,
        animationSpec = tween(Motion.Quick),
        label = "rowColor",
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .revealOnEnter(index)
            .pressScale(interaction)
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
            .padding(start = (entry.indent * 20).dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = "${entry.number}  ${entry.title}",
            color = titleColor,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
        )
        Canvas(
            modifier = Modifier
                .weight(1f)
                .height(16.dp)
                .padding(horizontal = 8.dp),
        ) {
            val y = size.height - 4.dp.toPx()
            drawLine(
                color = CMuted.copy(alpha = 0.6f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(0.1f, 6.dp.toPx()), 0f),
            )
        }
        Text(text = entry.status, color = CTeal, fontSize = 15.sp, fontFamily = FontFamily.Monospace)
    }
}

@Composable
fun SectionPlaceholder(entry: ContentsEntry, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CBackground)
            .safeContentPadding()
            .padding(24.dp),
    ) {
        Text(
            text = "< Back to contents",
            color = CPurple,
            fontSize = 15.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.clickable(onClick = onBack).padding(vertical = 8.dp),
        )
        Spacer(Modifier.height(24.dp))
        Text("${entry.number}  ${entry.title}", color = CText, fontSize = 22.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Coming soon. This is a placeholder until the section is built.", color = CMuted, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
    }
}