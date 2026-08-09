package com.monettheme.sample.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monettheme.api.ThemeColors
import com.monettheme.client.MonetColorClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SampleViewModel(application: Application) : AndroidViewModel(application) {

    var isLoading by mutableStateOf(true)
        private set

    var themeColors by mutableStateOf<ThemeColors?>(null)
        private set

    var isDarkTheme by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val client = MonetColorClient(application)

    init {
        loadFromWallpaper()
    }

    fun loadFromWallpaper() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                if (!client.connect()) {
                    errorMessage = "未连接到 MonetCore Service，请先安装 monet-service"
                    isLoading = false
                    return@launch
                }
                delay(2000)
                val bundle = client.generateThemeFromWallpaper(isDarkTheme)
                themeColors = ThemeColors.fromBundle(bundle)
                client.disconnect()
            } catch (e: Exception) {
                errorMessage = e.message ?: "未知错误"
            } finally {
                isLoading = false
            }
        }
    }

    fun loadFromColor(seedColor: Int) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                if (!client.connect()) {
                    errorMessage = "未连接到 MonetCore Service"
                    isLoading = false
                    return@launch
                }
                delay(1000)
                val bundle = client.generateThemeFromColor(seedColor, isDarkTheme)
                themeColors = ThemeColors.fromBundle(bundle)
                client.disconnect()
            } catch (e: Exception) {
                errorMessage = e.message ?: "未知错误"
            } finally {
                isLoading = false
            }
        }
    }

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme
        loadFromWallpaper()
    }

    override fun onCleared() {
        super.onCleared()
        client.disconnect()
    }
}
