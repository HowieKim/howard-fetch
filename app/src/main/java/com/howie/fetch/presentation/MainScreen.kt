package com.howie.fetch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.howie.fetch.R
import com.howie.fetch.data.repo.models.FetchDomainData
import com.howie.ui.theme.AppTheme

@Composable
fun MainScreen(
    sortedData: List<FetchDomainData>,
    listIdGroupList: List<Int>,
    onListIdGroupChanged: (Int?) -> Unit,
    currentSelectedListId: Int?,
    showLoadingLiveData: LiveData<Boolean>,
    showSnackBarLiveData: LiveData<SnackbarMessage>,
) {

    BaseScreen(
        showLoadingLiveData = showLoadingLiveData,
        showSnackBarLiveData = showSnackBarLiveData,
    ) { innerPadding ->
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(AppTheme.dimens.medium)
            ) {
                DropDown(
                    label = stringResource(id = R.string.group_by_list_id),
                    items = listIdGroupList,
                    selectedItem = currentSelectedListId,
                    onItemSelected = onListIdGroupChanged,
                    hasNullOption = true,
                    nullOptionText = stringResource(
                        id = R.string.none_selected
                    ),
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.small))
            LazyColumn(
                contentPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding())
            ) {
                itemsIndexed(sortedData) { index, item ->
                    if (index > 0) {
                        Divider()
                    }
                    ListItem(
                        listId = item.listId,
                        name = item.name,
                        modifier = Modifier.padding(horizontal = AppTheme.dimens.medium)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val testList = listOf(
        FetchDomainData(id = 0, listId = 1, name = "blah"),
        FetchDomainData(id = 2, listId = 2, name = null),
        FetchDomainData(id = 3, listId = 3, name = "blahblah")
    )

    AppTheme {
        MainScreen(
            sortedData = testList,
            listIdGroupList = listOf(1, 2, 3),
            currentSelectedListId = 1,
            onListIdGroupChanged = { },
            showLoadingLiveData = MutableLiveData(),
            showSnackBarLiveData = MutableLiveData(),
        )
    }
}
