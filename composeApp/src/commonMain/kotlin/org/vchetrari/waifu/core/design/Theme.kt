package org.vchetrari.waifu.core.design

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
fun WaifuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes.copy(
            medium = RoundedCornerShape(16.dp),
        ),
    ) {
        CompositionLocalProvider(
            content = content,
        )
    }
}