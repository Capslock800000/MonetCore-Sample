package com.monettheme.sample.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.monettheme.api.ThemeColors
import com.monettheme.client.MonetColorClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
    val isLoading: Boolean = true,
    val loadingMessage: String = "正在连接 Monet Service…",
    val isConnected: Boolean = false,
    val themeColors: ThemeColors? = null,
    val isDarkTheme: Boolean = false,
    val seedColor: Int = 0xFF6750A4.toInt(),
    val errorMessage: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val client = MonetColorClient(application)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        connectToService()
    }

    private fun connectToService() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loadingMessage = "正在连接 Monet Service…") }

            delay(1000)

            val connected = try {
                client.connect()
            } catch (e: Exception) {
                false
            }

            _uiState.update {
                it.copy(
                    isLoading = false,
                    isConnected = connected,
                    loadingMessage = if (connected) "连接成功" else "未安装 Monet Service，请先安装"
                )
            }

            if (connected) {
                generateFromColor()
            }
        }
    }

    fun retryConnection() {
        connectToService()
    }

    fun toggleTheme() {
        val newDark = !_uiState.value.isDarkTheme
        _uiState.update { it.copy(isDarkTheme = newDark) }
        if (_uiState.value.isConnected) {
            generateFromColor()
        }
    }

    fun setSeedColor(color: Int) {
        _uiState.update { it.copy(seedColor = color) }
    }

    fun generateFromWallpaper() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loadingMessage = "正在从壁纸提取颜色…") }

            try {
                val theme = client.generateThemeFromWallpaper(_uiState.value.isDarkTheme)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        themeColors = theme,
                        errorMessage = if (theme == null) "生成主题失败" else null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message ?: "未知错误")
                }
            }
        }
    }

    fun generateFromColor() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, loadingMessage = "正在生成主题…") }

            try {
                val theme = client.generateThemeFromColor(
                    seedColor = _uiState.value.seedColor,
                    darkTheme = _uiState.value.isDarkTheme
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        themeColors = theme,
                        errorMessage = if (theme == null) "生成主题失败" else null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message ?: "未知错误")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.disconnect()
    }
}
