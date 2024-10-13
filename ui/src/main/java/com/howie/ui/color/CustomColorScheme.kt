package com.howie.ui.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorScheme(
    val primary: Color,
    val secondary: Color,
    val warning: Color,
    val background: Color,
)
