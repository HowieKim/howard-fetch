package com.howie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.howie.ui.color.CustomColorScheme
import com.howie.ui.typography.CustomTypography

val LocalCustomColorScheme = staticCompositionLocalOf {
    CustomColorScheme(
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        background = Color.Unspecified,
        warning = Color.Unspecified
    )
}

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        titleSmall = TextStyle.Default,
        titleLarge = TextStyle.Default,
        bodySmall = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
    )
}

val LocalDimensions = staticCompositionLocalOf {
    Dimensions(
        small = Dp.Unspecified,
        medium = Dp.Unspecified,
        large = Dp.Unspecified,
    )
}


@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val customColorScheme = if (useDarkTheme) {
        DarkCustomColorScheme
    } else {
        LightCustomColorScheme
    }
    val materialColorScheme = if (useDarkTheme) {
        DarkMaterialColorScheme
    } else {
        LightMaterialColorScheme
    }

    val defaultDimensions = DefaultDimensions
    val customTypography = DefaultCustomTypography
    val materialTypography = DefaultMaterialTypography

    CompositionLocalProvider(
        LocalCustomColorScheme provides customColorScheme,
        LocalDimensions provides defaultDimensions,
        LocalCustomTypography provides customTypography
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            typography = materialTypography,
            content = content
        )
    }
}

object AppTheme {
    val colors: CustomColorScheme
        @Composable
        get() = LocalCustomColorScheme.current

    val dimens: Dimensions
        @Composable
        get() = LocalDimensions.current

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
}