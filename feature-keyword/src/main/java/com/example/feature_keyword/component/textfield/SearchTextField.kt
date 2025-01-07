package com.example.feature_keyword.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.PreviewComponentWrapper
import com.example.feature_keyword.mock.MockKeyword.KEYWORD

/**
 * 検索テキストフィールド
 * @param focusRequester フォーカスリクエスター
 * @param query クエリ
 * @param onQueryChange クエリ変更時のコールバック
 * @param modifier Modifier
 */
@Composable
fun SearchTextField(
    focusRequester: FocusRequester,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = query)) }
    val showClearButton = textFieldValue.text.isNotBlank()

    LaunchedEffect(query) {
        if (query != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = query,
                selection = TextRange(query.length)
            )
        }
    }

    LaunchedEffect(Unit) { focusRequester.requestFocus() }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.padding(start = 4.dp)
        )
        CustomTextField(
            focusRequester = focusRequester,
            textFieldValue = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onQueryChange(it.text)
            },
            showClearButton = showClearButton,
            onClearClick = {
                textFieldValue = TextFieldValue()
                onQueryChange("")
            }
        )
    }
}

@Suppress("UnusedPrivateMember")
@Preview
@Composable
private fun PreviewSearchTextField() {
    PreviewComponentWrapper {
        Column {
            SearchTextField(
                focusRequester = FocusRequester(),
                query = KEYWORD,
                onQueryChange = {}
            )
            Spacer(modifier = Modifier.padding(8.dp))
            SearchTextField(
                focusRequester = FocusRequester(),
                query = "",
                onQueryChange = {}
            )
        }
    }
}
