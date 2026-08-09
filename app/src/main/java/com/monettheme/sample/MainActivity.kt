package com.monettheme.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monettheme.client.MonetTheme
import com.monettheme.sample.ui.screens.LoadingScreen
import com.monettheme.sample.ui.screens.MainScreen
import com.monettheme.sample.viewmodel.SampleViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: SampleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val colors = viewModel.themeColors
            val loading = viewModel.isLoading
            val error = viewModel.errorMessage

            when {
                loading -> {
                    LoadingScreen(message = "正在核对信息…")
                }
                error != null && colors == null -> {
                    ErrorScreen(
                        message = error,
                        onRetry = { viewModel.loadFromWallpaper() }
                    )
                }
                colors != null -> {
                    MonetTheme(colors = colors) {
                        MainScreen(
                            themeColors = colors,
                            isDarkTheme = viewModel.isDarkTheme,
                            onToggleTheme = { viewModel.toggleTheme() },
                            onPickColor = { viewModel.loadFromColor(it) },
                            onUseWallpaper = { viewModel.loadFromWallpaper() }
                        )
                    }
                }
                else -> {
                    LoadingScreen(message = "正在核对信息…")
                }
            }
        }
    }
}

@Composable
private fun ErrorScreen(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "连接失败",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3C4043)
            )
            Text(
                text = message,
                fontSize = 14.sp,
                color = Color(0xFF5F6368)
            )
            Button(onClick = onRetry) {
                Text("重试")
            }
        }
    }
}
