package com.luukitoo.animapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text(text = "Search") },
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                placeholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch.invoke(query)
                    focusManager.clearFocus()
                }
            ),
        )
        FloatingActionButton(
            onClick = {
                onSearch.invoke(query)
                focusManager.clearFocus()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    AnimAppTheme {
        SearchField(
            query = "",
            onQueryChange = { },
            onSearch = { },
        )
    }
}