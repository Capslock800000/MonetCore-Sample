package com.monettheme.sample.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.monettheme.api.ThemeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    themeColors: ThemeColors,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onPickColor: (Int) -> Unit,
    onUseWallpaper: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MonetCore Sample") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(themeColors.primary),
                    titleContentColor = Color(themeColors.onPrimary)
                ),
                actions = {
                    IconButton(onClick = onToggleTheme) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle",
                            tint = Color(themeColors.onPrimary)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onUseWallpaper,
                containerColor = Color(themeColors.secondaryContainer),
                contentColor = Color(themeColors.onSecondaryContainer)
            ) {
                Icon(Icons.Default.Wallpaper, "Wallpaper")
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(themeColors.background))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Material 3 主题色板",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(themeColors.onBackground),
                fontWeight = FontWeight.Bold
            )

            ColorGrid(themeColors = themeColors)

            Divider(color = Color(themeColors.outlineVariant))

            Text(
                text = "预设种子色",
                style = MaterialTheme.typography.titleMedium,
                color = Color(themeColors.onBackground)
            )

            PresetColorChips(
                themeColors = themeColors,
                onColorSelected = onPickColor
            )
        }
    }
}

@Composable
private fun ColorGrid(themeColors: ThemeColors) {
    val items = listOf(
        "Primary" to themeColors.primary,
        "OnPrimary" to themeColors.onPrimary,
        "PrimaryC" to themeColors.primaryContainer,
        "Secondary" to themeColors.secondary,
        "Tertiary" to themeColors.tertiary,
        "Error" to themeColors.error,
        "Surface" to themeColors.surface,
        "SurfaceV" to themeColors.surfaceVariant,
        "Background" to themeColors.background,
        "Outline" to themeColors.outline,
        "Inverse" to themeColors.inversePrimary,
        "Scrim" to themeColors.scrim,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(280.dp)
    ) {
        items(items) { (name, color) ->
            ColorChip(name = name, color = color)
        }
    }
}

@Composable
private fun ColorChip(name: String, color: Int) {
    val bg = Color(color)
    val lum = (0.299 * ((color shr 16) and 0xFF) +
            0.587 * ((color shr 8) and 0xFF) +
            0.114 * (color and 0xFF)) / 255
    val fg = if (lum > 0.5) Color.Black else Color.White

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            color = fg,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun PresetColorChips(
    themeColors: ThemeColors,
    onColorSelected: (Int) -> Unit
) {
    val presets = listOf(
        0xFF6750A4.toInt() to "Pixel Purple",
        0xFF4CAF50.toInt() to "Green",
        0xFFF44336.toInt() to "Red",
        0xFF2196F3.toInt() to "Blue",
        0xFFFF9800.toInt() to "Orange",
        0xFF9C27B0.toInt() to "Violet",
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        presets.forEach { (color, label) ->
            AssistChip(
                onClick = { onColorSelected(color) },
                label = { Text(label) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color(color).copy(alpha = 0.15f),
                    labelColor = Color(themeColors.onSurface)
                )
            )
        }
    }
}
