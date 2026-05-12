package edu.itschool.abitpro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val darkColors = darkColorScheme(
        primary = DForeground1,
    )

    val lightColors = lightColorScheme(
        primary = LForeground1
    )


    MaterialTheme(
        colorScheme = lightColors,
        content = content
    )


}