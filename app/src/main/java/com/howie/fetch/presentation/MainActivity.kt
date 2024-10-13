package com.howie.fetch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.howie.fetch.R
import com.howie.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.accessibility_title)
        enableEdgeToEdge()
        setContent {
            val sortedData by viewModel.sortedFetchData.observeAsState(emptyList())
            val listIdGroups by viewModel.listIdGroups.observeAsState(emptyList())
            val currentSelectedListId by viewModel.currentSelectedListId.observeAsState()
            AppTheme {
                MainScreen(
                    sortedData = sortedData,
                    listIdGroupList = listIdGroups,
                    currentSelectedListId = currentSelectedListId,
                    onListIdGroupChanged = viewModel::onGroupChanged,
                    showLoadingLiveData = viewModel.showLoading,
                    showSnackBarLiveData = viewModel.showSnackbarError,
                )
            }
        }
    }
}
