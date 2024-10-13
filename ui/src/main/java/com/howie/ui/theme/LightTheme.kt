package com.howie.ui.theme

import androidx.compose.material3.lightColorScheme
import com.howie.ui.color.AppColor
import com.howie.ui.color.CustomColorScheme

val LightCustomColorScheme = CustomColorScheme(
    primary = AppColor.Black,
    secondary = AppColor.Gray,
    background = AppColor.White,
    warning = AppColor.DarkRed
)

val LightMaterialColorScheme = lightColorScheme(
    primary = LightCustomColorScheme.primary,
    secondary = LightCustomColorScheme.secondary,
    background = LightCustomColorScheme.background,
    error = LightCustomColorScheme.warning
)