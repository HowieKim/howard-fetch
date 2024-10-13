package com.howie.fetch.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.howie.fetch.R
import com.howie.ui.theme.AppTheme

@Composable
fun ListItem(listId: Int, name: String?, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(AppTheme.colors.background)
            .padding(vertical = AppTheme.dimens.small)
    ) {
        TextLabelAndDescription(
            label = stringResource(id = R.string.list_id),
            description = listId.toString()
        )
        Spacer(modifier = Modifier.width(AppTheme.dimens.large))
        TextLabelAndDescription(
            label = stringResource(
                id = R.string.name
            ),
            description = name
        )
    }
}

@Composable
fun TextLabelAndDescription(label: String, description: String?) {
    Column {
        Text(text = label, style = AppTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(AppTheme.dimens.small))
        Text(
            text = description ?: "", color = AppTheme.colors.secondary,
            style = AppTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ListItem(listId = 1, name = "test")
    }
}