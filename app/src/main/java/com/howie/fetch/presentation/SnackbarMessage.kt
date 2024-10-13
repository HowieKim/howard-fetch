package com.howie.fetch.presentation

import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration

data class SnackbarMessage(
    @StringRes val stringId: Int,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val retryButtonData: ButtonData,
)

data class ButtonData(@StringRes val buttonLabelStringRes: Int, val onClick: () -> Unit)
