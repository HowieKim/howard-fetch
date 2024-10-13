package com.howie.fetch.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.howie.fetch.R
import com.howie.ui.theme.AppTheme


@Composable
fun <T : Any> DropDown(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T?) -> Unit,
    label: String,
    hasNullOption: Boolean = true,
    nullOptionText: String? = null
) {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Column() {
        Row(
            modifier = Modifier
                .clickable {
                    isDropDownExpanded.value = true
                }
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = selectedItem?.toString() ?: nullOptionText ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = false,
                textStyle = AppTheme.typography.labelMedium,
                label = {
                    Text(text = label, style = AppTheme.typography.labelMedium)
                },
                trailingIcon = {
                    Icon(
                        tint = AppTheme.colors.secondary,
                        painter = painterResource(id = com.howie.ui.R.drawable.ic_drop_down),
                        contentDescription = stringResource(id = R.string.drop_down_content_description)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledLabelColor = AppTheme.colors.primary,
                    disabledBorderColor = AppTheme.colors.primary,
                    disabledTextColor = AppTheme.colors.primary,
                    disabledPlaceholderColor = AppTheme.colors.primary,
                )
            )

        }
        DropdownMenu(
            expanded = isDropDownExpanded.value,
            onDismissRequest = {
                isDropDownExpanded.value = false
            }) {
            if (hasNullOption) {
                DropdownMenuItem(text = {
                    Text(text = nullOptionText ?: "")
                },
                    onClick = {
                        isDropDownExpanded.value = false
                        onItemSelected(null)
                    })
            }

            items.forEachIndexed { index, item ->
                DropdownMenuItem(text = {
                    Text(text = item.toString())
                },
                    onClick = {
                        isDropDownExpanded.value = false
                        onItemSelected(item)
                    })
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        DropDown(
            items = listOf(1, 2, 3),
            selectedItem = 1,
            onItemSelected = {},
            hasNullOption = false,
            nullOptionText = null,
            label = "label"
        )
    }
}