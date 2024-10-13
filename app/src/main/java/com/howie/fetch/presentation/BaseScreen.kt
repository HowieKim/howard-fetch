package com.howie.fetch.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import com.howie.ui.theme.AppTheme

@Composable
fun BaseScreen(
    showLoadingLiveData: LiveData<Boolean>,
    showSnackBarLiveData: LiveData<SnackbarMessage>,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = { },
    content: @Composable (PaddingValues) -> Unit = { }
) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val showLoading by showLoadingLiveData.observeAsState(false)
    val showSnackbarMessage by showSnackBarLiveData.observeAsState()
    val messageString: String? = showSnackbarMessage?.let { stringResource(id = it.stringId) }
    val messageDuration = showSnackbarMessage?.duration
    val snackbarRetryButtonLabel = showSnackbarMessage?.retryButtonData?.let { stringResource(id = it.buttonLabelStringRes) }
    val snackbarRetryOnButtonClick = showSnackbarMessage?.retryButtonData?.onClick

    LaunchedEffect(showSnackbarMessage) {
        if (messageString != null && messageDuration != null) {
            val result = snackbarHostState.showSnackbar(
                message = messageString,
                actionLabel = snackbarRetryButtonLabel,
                withDismissAction = (messageDuration == SnackbarDuration.Indefinite),
                duration = messageDuration
            )
            if (result == SnackbarResult.ActionPerformed) {
                snackbarRetryOnButtonClick?.invoke()
            }
        }
    }

    Scaffold(
        topBar = topBar,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(
                        snackbarData = it,
                        containerColor = AppTheme.colors.warning,
                        contentColor = AppTheme.colors.primary,
                        actionColor = AppTheme.colors.secondary,
                        actionContentColor = AppTheme.colors.secondary,
                        dismissActionContentColor = AppTheme.colors.secondary,
                    )
                }
            )
        },
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeContent)
            .safeContentPadding()
            .safeGesturesPadding(),
        content = content
    )

    if (showLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.padding(AppTheme.dimens.large))
        }
    }
}