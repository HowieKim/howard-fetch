package com.howie.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
)

val DefaultDimensions = Dimensions(
    small = 8.dp,
    medium = 16.dp,
    large = 24.dp,
)
