package com.howie.ui.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTextStyle {

    val TitleLarge = TextStyle(
        fontFamily = AppFont.Plain,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp
    )

    val TitleSmall = TextStyle(
        fontFamily = AppFont.Plain,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )

    val Bodylarge = TextStyle(
        fontFamily = AppFont.Plain,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    )

    val BodySmall = TextStyle(
        fontFamily = AppFont.Plain,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

    val LabelMedium = TextStyle(
        fontFamily = AppFont.Plain,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
}