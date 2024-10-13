package com.howie.ui.theme

import androidx.compose.material3.darkColorScheme
import com.howie.ui.color.AppColor
import com.howie.ui.color.CustomColorScheme

val DarkCustomColorScheme = CustomColorScheme(
    primary = AppColor.White,
    secondary = AppColor.Gray,
    background = AppColor.Black,
    warning = AppColor.LightRed
)

val DarkMaterialColorScheme = darkColorScheme(
    primary = DarkCustomColorScheme.primary,
    secondary = DarkCustomColorScheme.secondary,
    background = DarkCustomColorScheme.background,
    error = DarkCustomColorScheme.warning
)