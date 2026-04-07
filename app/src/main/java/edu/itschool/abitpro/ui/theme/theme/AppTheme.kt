package edu.ITSchool.abitpro.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val darkColors = darkColorScheme(
        primary = Foreground,
    )

    MaterialTheme(
        colorScheme = darkColors,
        content = content
    )


}