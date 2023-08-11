package com.luukitoo.animapp.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luukitoo.animapp.presentation.ui.theme.AnimAppTheme

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    minLines: Int,
    textStyle: TextStyle = LocalTextStyle.current
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = text,
            maxLines = if (isExpanded) Int.MAX_VALUE else minLines,
            overflow = TextOverflow.Ellipsis,
            style = textStyle
        )
        IconButton(
            onClick = { isExpanded = !isExpanded }
        ) {
            Icon(
                imageVector = if (isExpanded) {
                    Icons.Outlined.KeyboardArrowUp
                } else {
                    Icons.Outlined.KeyboardArrowDown
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}


@Preview
@Composable
fun ExpandableTextPreview() {
    AnimAppTheme {
        Surface {
            ExpandableText(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                minLines = 3
            )
        }
    }
}