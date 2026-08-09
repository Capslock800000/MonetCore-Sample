package com.monettheme.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monettheme.sample.ui.screens.HomeScreen
import com.monettheme.sample.ui.screens.SplashScreen
import com.monettheme.sample.ui.theme.MonetSampleTheme
import com.monettheme.sample.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: MainViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            MonetSampleTheme(
                darkTheme = uiState.isDarkTheme,
                dynamicColor = false,
                themeColors = uiState.themeColors
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (uiState.isLoading) {
                        SplashScreen(
                            message = uiState.loadingMessage,
                            onRetry = { viewModel.retryConnection() }
                        )
                    } else {
                        HomeScreen(
                            themeColors = uiState.themeColors,
                            isDarkTheme = uiState.isDarkTheme,
                            seedColor = uiState.seedColor,
                            onThemeToggle = { viewModel.toggleTheme() },
                            onColorSelected = { viewModel.setSeedColor(it) },
                            onGenerateFromWallpaper = { viewModel.generateFromWallpaper() },
                            onGenerateFromColor = { viewModel.generateFromColor() }
                        )
                    }
                }
            }
        }
    }
}
