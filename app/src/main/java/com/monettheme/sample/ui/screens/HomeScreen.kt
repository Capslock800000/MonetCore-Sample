package com.monettheme.sample.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monettheme.api.ThemeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    themeColors: ThemeColors?,
    isDarkTheme: Boolean,
    seedColor: Int,
    onThemeToggle: () -> Unit,
    onColorSelected: (Int) -> Unit,
    onGenerateFromWallpaper: () -> Unit,
    onGenerateFromColor: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MonetCore Sample") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = onThemeToggle) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = if (isDarkTheme) "切换到浅色模式" else "切换到深色模式"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ControlPanel(
                isDarkTheme = isDarkTheme,
                seedColor = seedColor,
                onThemeToggle = onThemeToggle,
                onColorSelected = onColorSelected,
                onGenerateFromWallpaper = onGenerateFromWallpaper,
                onGenerateFromColor = onGenerateFromColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (themeColors != null) {
                Text(
                    text = "生成的 Material 3 色板",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(12.dp))
                ColorPaletteGrid(themeColors = themeColors)
            } else {
                Text(
                    text = "点击上方按钮生成主题",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
