package com.howie.ui.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class CustomTypography(
    val titleSmall: TextStyle,
    val titleLarge: TextStyle,

    val bodySmall: TextStyle,
    val bodyLarge: TextStyle,

    val labelMedium: TextStyle
)
