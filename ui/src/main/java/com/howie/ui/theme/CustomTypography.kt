package com.howie.ui.theme

import androidx.compose.material3.Typography
import com.howie.ui.typography.AppTextStyle
import com.howie.ui.typography.CustomTypography

val DefaultCustomTypography = CustomTypography(
    titleSmall = AppTextStyle.TitleSmall,
    titleLarge = AppTextStyle.TitleLarge,
    bodySmall = AppTextStyle.BodySmall,
    bodyLarge = AppTextStyle.Bodylarge,
    labelMedium = AppTextStyle.LabelMedium,
)

val DefaultMaterialTypography = Typography(
    titleSmall = DefaultCustomTypography.titleSmall,
    titleLarge = DefaultCustomTypography.titleLarge,
    bodySmall = DefaultCustomTypography.bodySmall,
    bodyLarge = DefaultCustomTypography.bodyLarge,
    labelMedium = DefaultCustomTypography.labelMedium,
)