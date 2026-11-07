package io.github.collin440.tsebo

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput

private val CBackground = Color(0xFF0D1117)
private val CText = Color(0xFFE6EDF3)
private val CMuted = Color(0xFF8B949E)
private val CPurple = Color(0xFF7F52FF)
private val CTeal = Color(0xFF14B8A6)

data class ContentsEntry(
    val id: String,
    val number: String,
    val title: String,
    val status: String,
    val indent: Int = 0,
)

// Placeholder numbers until Supabase supplies real ones.
private val entries = listOf(
    ContentsEntry("brief", "1", "The Brief", "8 waiting"),
    ContentsEntry("hubs", "2", "Hubs", "4"),
    ContentsEntry("hub-mobile-web", "2.1", "Mobile & Web", "12", indent = 1),
    ContentsEntry("hub-cloud", "2.2", "Cloud & Infra", "9", indent = 1),
    ContentsEntry("hub-ai", "2.3", "AI & ML", "9", indent = 1),
    ContentsEntry("hub-africa", "2.4", "Africa Business Tech", "6", indent = 1),
    ContentsEntry("build-logs", "3", "Build Logs", "open"),
    ContentsEntry("events", "4", "Events", "near you"),
    ContentsEntry("lens", "5", "Your lens", "Builder"),
)

@Composable
fun ContentsScreen(lastOpened: ContentsEntry?, onOpen: (ContentsEntry) -> Unit) {
    var showVersion by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CBackground)
            .safeContentPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Text(
            "TSEBO(1)",
            color = CText,
            fontSize = 22.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(onLongPress = { showVersion = !showVersion })
            },
        )
        if (showVersion) {
            Text(
                "v0.1.0 pre-alpha | built with Kotlin Multiplatform",
                color = CTeal,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
            )
        }
        Text("research. discover. engineer.", color = CMuted, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
        Text("Today's edition", color = CTeal, fontSize = 14.sp, fontFamily = FontFamily.Monospace)
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
    val reveal = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(index * 70L)
        reveal.animateTo(1f, tween(350))
    }
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(reveal.value)
            .offset(x = ((1f - reveal.value) * 16f).dp)
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
            .padding(start = (entry.indent * 20).dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = "${entry.number}  ${entry.title}",
            color = if (pressed) CPurple else CText,
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