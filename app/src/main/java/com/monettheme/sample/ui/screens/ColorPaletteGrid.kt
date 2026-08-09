package com.monettheme.sample.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.monettheme.api.ThemeColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorPaletteGrid(themeColors: ThemeColors) {
    val colorItems = listOf(
        "Primary" to Color(themeColors.primary),
        "On Primary" to Color(themeColors.onPrimary),
        "Primary Container" to Color(themeColors.primaryContainer),
        "On Primary C." to Color(themeColors.onPrimaryContainer),
        "Secondary" to Color(themeColors.secondary),
        "On Secondary" to Color(themeColors.onSecondary),
        "Secondary Container" to Color(themeColors.secondaryContainer),
        "On Secondary C." to Color(themeColors.onSecondaryContainer),
        "Tertiary" to Color(themeColors.tertiary),
        "On Tertiary" to Color(themeColors.onTertiary),
        "Tertiary Container" to Color(themeColors.tertiaryContainer),
        "On Tertiary C." to Color(themeColors.onTertiaryContainer),
        "Error" to Color(themeColors.error),
        "On Error" to Color(themeColors.onError),
        "Error Container" to Color(themeColors.errorContainer),
        "On Error C." to Color(themeColors.onErrorContainer),
        "Background" to Color(themeColors.background),
        "On Background" to Color(themeColors.onBackground),
        "Surface" to Color(themeColors.surface),
        "On Surface" to Color(themeColors.onSurface),
        "Surface Variant" to Color(themeColors.surfaceVariant),
        "On Surface Var." to Color(themeColors.onSurfaceVariant),
        "Outline" to Color(themeColors.outline),
        "Inverse Primary" to Color(themeColors.inversePrimary),
        "Inverse Surface" to Color(themeColors.inverseSurface),
        "Inverse On Surface" to Color(themeColors.inverseOnSurface),
    )

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 2
    ) {
        colorItems.forEach { (name, color) ->
            ColorSwatch(name = name, color = color)
        }
    }
}

@Composable
private fun ColorSwatch(name: String, color: Color) {
    val isLight = color.luminance() > 0.5f

    Column(
        modifier = Modifier
            .width(160.dp)
            .height(72.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            color = if (isLight) Color.Black else Color.White,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
